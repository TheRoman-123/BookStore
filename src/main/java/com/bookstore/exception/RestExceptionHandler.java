package com.bookstore.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> onConstraintValidationException(
            IllegalArgumentException ex
    ) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
