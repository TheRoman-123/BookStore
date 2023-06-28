package com.bookstore.service;

import com.bookstore.dto.LiteraryWorkDto;
import com.bookstore.entity.LiteraryWork;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.LiteraryWorkRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LiteraryWorkService {
    private final LiteraryWorkRepository literaryWorkRepository;
    private final AuthorRepository authorRepository;

    public List<LiteraryWorkDto> getLiteraryWorks(int page, int size, String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return literaryWorkRepository.findAll(pageable)
                .map(LiteraryWorkDto::new)
                .toList();
    }

    public void createLiteraryWorks(List<String> literaryWorkNames) {
        /*if (literaryWorkRepository.existsByTitleAndAuthorId(literaryWorkNames)) {
            throw new EntityExistsException("LiteraryWork with provided name already exists");
        }
        List<LiteraryWork> literaryWorks = literaryWorkNames.stream().map(LiteraryWork::new).toList();
        literaryWorkRepository.saveAll(literaryWorks);*/
    }

    public Long createLiteraryWork(LiteraryWorkDto literaryWorkDto) {
        if (literaryWorkRepository.existsByTitleAndAuthorId(
                literaryWorkDto.getTitle(), literaryWorkDto.getAuthorId())
        ) {
            throw new EntityExistsException("LiteraryWork already exists");
        }
        LiteraryWork literaryWork = LiteraryWork.builder()
                .title(literaryWorkDto.getTitle())
                .yearWritten(literaryWorkDto.getYearWritten())
                .author(authorRepository.getReferenceById(literaryWorkDto.getId()))
                .build();

        literaryWorkRepository.save(literaryWork);

        return literaryWork.getId();
    }

    public void updateLiteraryWork(Long literaryWorkId, LiteraryWorkDto literaryWorkDto) {
        Optional<LiteraryWork> literaryWorkOptional = literaryWorkRepository.findById(literaryWorkId);
        if (literaryWorkOptional.isEmpty()) {
            throw new EntityNotFoundException("LiteraryWork with provided id does not exist");
        }

        LiteraryWork literaryWork = literaryWorkOptional.get();
        literaryWork.setTitle(literaryWorkDto.getTitle());
        literaryWork.setYearWritten(literaryWorkDto.getYearWritten());

        literaryWorkRepository.save(literaryWork);
    }

    public void deleteLiteraryWork(Long literaryWorkId) {
        literaryWorkRepository.deleteById(literaryWorkId);
    }

}
