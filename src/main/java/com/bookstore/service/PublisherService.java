package com.bookstore.service;

import com.bookstore.dto.PublisherDto;
import com.bookstore.entity.Publisher;
import com.bookstore.repository.PublisherRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublisherService {
    private final PublisherRepository publisherRepository;

    public List<PublisherDto> getPublishers() {

        return publisherRepository.findAll()
                .stream()
                .map(PublisherDto::new)
                .toList();

    }

    public void createPublishers(List<String> publisherNames) {
        if (publisherRepository.existsByPublisherNameIn(publisherNames)) {
            throw new EntityExistsException("Publisher with provided name already exists");
        }
        List<Publisher> publishers = publisherNames.stream().map(Publisher::new).toList();
        publisherRepository.saveAll(publishers);
    }

    public Long createPublisher(String publisherName) {
        if (publisherRepository.existsByPublisherNameIgnoreCase(publisherName)) {
            throw new EntityExistsException("Publisher with provided name already exists");
        }
        Publisher publisher = new Publisher(publisherName);
        publisherRepository.save(publisher);
        return publisher.getId();
    }

    public void updatePublisher(Long publisherId, PublisherDto publisherDto) {
        Optional<Publisher> publisherOptional = publisherRepository.findById(publisherId);
        if (publisherOptional.isEmpty()) {
            throw new EntityNotFoundException("Publisher with provided id does not exist");
        }

        Publisher publisher = publisherOptional.get();
        publisher.setPublisherName(publisherDto.getPublisherName());

        publisherRepository.save(publisher);
    }

    public void deletePublisher(Long publisherId) {
        publisherRepository.deleteById(publisherId);
    }
}
