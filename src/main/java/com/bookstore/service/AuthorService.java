package com.bookstore.service;

import com.bookstore.dto.authorDto.AuthorDto;
import com.bookstore.dto.authorDto.ComplexAuthorDto;
import com.bookstore.dto.authorDto.SimpleAuthorDto;
import com.bookstore.entity.Author;
import com.bookstore.repository.AuthorRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public List<AuthorDto> getAuthors(boolean withLiteraryWorks) {
        Function<Author, AuthorDto> authorMapper = withLiteraryWorks ?
                ComplexAuthorDto::new :
                SimpleAuthorDto::new;

        return authorRepository.findAll()
                .stream()
                .map(authorMapper)
                .toList();
    }

    public void createAuthors(List<String> authorNames) {
        if (authorRepository.existsByNameIn(authorNames)) {
            throw new EntityExistsException("Author with provided name already exists");
        }
        List<Author> authors = authorNames.stream().map(Author::new).toList();
        authorRepository.saveAll(authors);
    }

    public Long createAuthor(String authorName) {
        if (authorRepository.existsByNameIgnoreCase(authorName)) {
            throw new EntityExistsException("Author with provided name already exists");
        }
        Author author = new Author(authorName);
        authorRepository.save(author);
        return author.getId();
    }

    public void updateAuthor(Long authorId, AuthorDto authorDto) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty()) {
            throw new EntityNotFoundException("Author with provided id does not exist");
        }

        Author author = authorOptional.get();
        author.setName(authorDto.getName());

        authorRepository.save(author);
    }

    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }
}
