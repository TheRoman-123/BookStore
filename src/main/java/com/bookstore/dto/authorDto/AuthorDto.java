package com.bookstore.dto.authorDto;

import com.bookstore.dto.LiteraryWorkDto;

import java.util.Set;

/**
 * DTO for {@link com.bookstore.entity.Author}
 */
public interface AuthorDto {
    Long getId();
    void setId(Long id);
    String getName();
    void setName(String authorName);
    default Set<LiteraryWorkDto> getLiteraryWorkDtos() {
        return null;
    }
    default void setLiteraryWorkDtos(Set<LiteraryWorkDto> literaryWorkDtos) {}
}
