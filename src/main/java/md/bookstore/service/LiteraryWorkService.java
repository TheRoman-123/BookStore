package md.bookstore.service;

import lombok.AllArgsConstructor;
import md.bookstore.repository.LiteraryWorkRepository;
import md.bookstore.dto.LiteraryWorkDto;
import md.bookstore.entity.LiteraryWork;
import md.bookstore.exception.IllegalPageException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LiteraryWorkService {
    private LiteraryWorkRepository literaryWorkRepository;

    // Later implement Pageable!
    public List<LiteraryWorkDto> getAllUntilLimit(Integer offset, Integer limit) {
        if (offset == null || limit == null || limit <= 0 || offset <= 0) {
            throw new IllegalPageException(offset, limit);
        }
        return literaryWorkRepository.findLiteraryWorkEntityWithOffsetAndLimit(offset, limit)
                .parallelStream()
                .map(LiteraryWorkDto::new)
                .collect(Collectors.toList());
    }

    public List<LiteraryWorkDto> getAll() {
        return literaryWorkRepository.findAll()
                .parallelStream()
                .map(LiteraryWorkDto::new)
                .collect(Collectors.toList());
    }

    public LiteraryWorkDto get(Long id) {
        return new LiteraryWorkDto(literaryWorkRepository.findById(id).orElseThrow());
    }

    public void createLiteraryWork(LiteraryWorkDto literaryWorkDto) {
        if (literaryWorkDto == null) {
            throw new NullPointerException("LiteraryWork is null. LiteraryWork not created.");
        }
        LiteraryWork literaryWork = new LiteraryWork();
        literaryWork.setTitle(literaryWorkDto.getTitle());
        literaryWorkRepository.save(literaryWork);
    }

    public void updateLiteraryWork(Long id, LiteraryWorkDto literaryWorkDto) {
        LiteraryWork literaryWork = literaryWorkRepository.getReferenceById(id);
        literaryWork.setTitle(literaryWorkDto.getTitle());
        literaryWorkRepository.save(literaryWork);
    }

    public void deleteLiteraryWork(Long id) {
        literaryWorkRepository.deleteById(id);
    }
}
