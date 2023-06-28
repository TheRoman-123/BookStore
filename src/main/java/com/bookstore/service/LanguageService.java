package com.bookstore.service;

import com.bookstore.dto.BookDto;
import com.bookstore.entity.Book;
import com.bookstore.entity.Language;
import com.bookstore.entity.LanguageCode;
import com.bookstore.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;

    public Set<Language> convertToLanguages(BookDto bookDto, Book book) {
        return bookDto.getLanguages()
                .stream()
                .map(
                        languageCode -> new Language(
                                new LanguageCode(languageCode, book.getId()),
                                book
                        )
                )
                .collect(Collectors.toSet());
    }

}
