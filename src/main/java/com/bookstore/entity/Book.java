package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 68548L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String title;

    private String author;

    private String publisher;

    private String publicationDate;

    private String language;

    private String category;

    private int numberOfPages;

    private double ourPrice;

    private boolean active = true;

    @Column(columnDefinition = "text")
    private String description;

    private int inStockNumber;

    @Transient
    private MultipartFile bookImage;
}
