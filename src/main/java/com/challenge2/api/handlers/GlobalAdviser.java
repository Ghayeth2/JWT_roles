package com.challenge2.api.handlers;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalAdviser {

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(final NoSuchElementException e) {
        return e.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error) -> {
            final String errorMessage = error.getDefaultMessage();
            final String errField = ((FieldError) error).getField();
            errors.put(errField, errorMessage);
        });
        return errors;
    }
}
