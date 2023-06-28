package com.bookstore.dto.authorDto;

import com.bookstore.dto.LiteraryWorkDto;
import com.bookstore.entity.Author;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ComplexAuthorDto implements AuthorDto, Serializable {
    private Long id;

    @Pattern(regexp = "^[A-Za-zА-Яа-я ]{2,}$", message = "Provide valid author name")
    private String name;

    private Set<LiteraryWorkDto> literaryWorkDtos;

    public ComplexAuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
//        this.literaryWorks = author.getLiteraryWorks()
//                .stream()
//                .map(Literar);
    }
}