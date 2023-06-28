package com.bookstore.dto;

import com.bookstore.entity.LiteraryWork;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.bookstore.entity.LiteraryWork}
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LiteraryWorkDto implements Serializable {

    private Long id;

    private String title;

    private Short yearWritten;

    private Long authorId;

    private String authorName;

    public LiteraryWorkDto(LiteraryWork literaryWork) {
        id = literaryWork.getId();
        title = literaryWork.getTitle();
        yearWritten = literaryWork.getYearWritten();
        authorId = literaryWork.getAuthor().getId();
        authorName = literaryWork.getAuthor().getName();
    }
}