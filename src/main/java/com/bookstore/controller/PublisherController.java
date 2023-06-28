package com.bookstore.controller;

import com.bookstore.dto.PublisherDto;
import com.bookstore.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publishers")
@RequiredArgsConstructor
public class PublisherController {
    private final PublisherService publisherService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PublisherDto> getPublishers() {
        return publisherService.getPublishers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long createPublisher(
            @RequestParam(value = "publisherName", required = false) String publisherName,
            @RequestBody(required = false) List<String> publisherNames
    ) {
        if (publisherName == null && publisherNames != null) {
            publisherService.createPublishers(publisherNames);
            return null;
        } else if (publisherName != null && publisherNames == null) {
            return publisherService.createPublisher(publisherName);
        } else {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updatePublisher(
            @PathVariable("id") Long publisherId,
            @RequestBody PublisherDto publisherDto
    ) {
        publisherService.updatePublisher(publisherId, publisherDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deletePublisher(
            @PathVariable("id") Long publisherId
    ) {
        publisherService.deletePublisher(publisherId);
    }

}
