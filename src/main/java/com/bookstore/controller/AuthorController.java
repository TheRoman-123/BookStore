package com.bookstore.controller;

import com.bookstore.dto.authorDto.AuthorDto;
import com.bookstore.dto.authorDto.SimpleAuthorDto;
import com.bookstore.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDto> getAuthors(
            @RequestParam("withLiteraryWorks") boolean withLiteraryWorks
    ) {
        return authorService.getAuthors(withLiteraryWorks);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long createAuthor(
            @RequestParam(value = "authorName", required = false) String authorName,
            @RequestBody(required = false) List<String> authorNames
    ) {
        if (authorName == null && authorNames != null) {
            authorService.createAuthors(authorNames);
            return null;
        } else if (authorName != null && authorNames == null) {
            return authorService.createAuthor(authorName);
        } else {
            throw new IllegalArgumentException("Invalid arguments");
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateAuthor(
            @PathVariable("id") Long authorId,
            @RequestBody @Valid SimpleAuthorDto authorDto
    ) {
        authorService.updateAuthor(authorId, authorDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAuthor(
            @PathVariable("id") Long authorId
    ) {
        authorService.deleteAuthor(authorId);
    }

}
