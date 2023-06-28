package com.bookstore.dto.authorDto;

import com.bookstore.entity.Author;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleAuthorDto implements AuthorDto, Serializable {

    private Long id;

    @Pattern(regexp = "^[A-Za-zА-Яа-я ]{2,}$", message = "Provide valid author name")
    private String name;

    public SimpleAuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}