package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.Book;
import com.bookstore.entity.Language;
import com.bookstore.entity.Publisher;
import com.bookstore.repository.BookRepository;
import com.bookstore.repository.LanguageRepository;
import com.bookstore.repository.PublisherRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {
    private final LanguageService languageService;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final LanguageRepository languageRepository;

    public List<BookDto> getBooks(int page, int size, String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        return bookRepository.findAll(pageable)
                .map(BookDto::new)
                .toList();

    }

    @Transactional
    public Long createBook(BookDto bookDto) {
        if (bookRepository.existsByTitleAndPublisherId(
                bookDto.getTitle(), bookDto.getPublisherDto().getPublisherId())
        ) {
            throw new EntityExistsException("Book already exists");
        }

        Publisher publisherToBind = publisherRepository.getReferenceById(bookDto.getPublisherDto().getPublisherId());

        Book book = Book.builder()
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .category(bookDto.getCategory())
                .price(bookDto.getPrice())
                .publicationDate(bookDto.getPublicationDate())
                .inStockNumber(bookDto.getInStockNumber())
                .publisher(publisherToBind)
                .build();

        bookRepository.save(book);

        Set<Language> languageBookRelationship = languageService.convertToLanguages(bookDto, book);

        languageRepository.saveAll(languageBookRelationship);

        return book.getId();
    }

    public void updateBook(Long bookId, BookDto bookDto) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) {
            throw new EntityNotFoundException("Book with provided id does not exist");
        }

        Book book = bookOptional.get();
        book.setTitle(bookDto.getTitle());
        book.setDescription(bookDto.getDescription());
        book.setCategory(bookDto.getCategory());
        book.setPrice(book.getPrice());
        book.setInStockNumber(bookDto.getInStockNumber());
        book.setPublicationDate(bookDto.getPublicationDate());

        bookRepository.save(book);

        Set<Language> languageBookRelationship = languageService.convertToLanguages(bookDto, book);

        languageRepository.saveAll(languageBookRelationship);
    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

}
