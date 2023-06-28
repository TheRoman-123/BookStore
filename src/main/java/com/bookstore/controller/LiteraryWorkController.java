package com.bookstore.controller;

import com.bookstore.dto.LiteraryWorkDto;
import com.bookstore.service.LiteraryWorkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/literaryWorks")
@RequiredArgsConstructor
public class LiteraryWorkController {
    private final LiteraryWorkService literaryWorkService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LiteraryWorkDto> getLiteraryWorks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        return literaryWorkService.getLiteraryWorks(page, size, sort);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long createLiteraryWork(
            @RequestBody LiteraryWorkDto literaryWorkDto
    ) {
        return literaryWorkService.createLiteraryWork(literaryWorkDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateLiteraryWork(
            @PathVariable("id") Long literaryWorkId,
            @RequestBody LiteraryWorkDto literaryWorkDto
    ) {
        literaryWorkService.updateLiteraryWork(literaryWorkId, literaryWorkDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteLiteraryWork(
            @PathVariable("id") Long literaryWorkId
    ) {
        literaryWorkService.deleteLiteraryWork(literaryWorkId);
    }

}
