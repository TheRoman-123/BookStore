package com.bookstore.dto;

import com.bookstore.entity.Publisher;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class PublisherDto {
    private final Long publisherId;
    private final String publisherName;

    public PublisherDto(Publisher publisher) {
        publisherId = publisher.getId();
        publisherName = publisher.getPublisherName();
    }


}
