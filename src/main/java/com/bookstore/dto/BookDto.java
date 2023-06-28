package com.bookstore.dto;

import com.bookstore.entity.Book;
import com.bookstore.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * DTO for {@link com.bookstore.entity.Book}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String category;
    private Integer inStockNumber;
    private Float price;
    private LocalDate publicationDate;

    private Set<String> languages;
    private PublisherDto publisherDto;
    private Set<LiteraryWorkDto> literaryWorks;

    public BookDto(Book book) {
        id = book.getId();
        title = book.getTitle();
        description = book.getDescription();
        category = book.getCategory();
        inStockNumber = book.getInStockNumber();
        price = book.getPrice();
        publicationDate = book.getPublicationDate();

        languages = book.getLanguages().stream().map(Language::toString).collect(Collectors.toSet());
        publisherDto = new PublisherDto(book.getPublisher());
    }
}