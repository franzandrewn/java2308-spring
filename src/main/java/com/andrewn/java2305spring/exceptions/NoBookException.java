package com.andrewn.java2305spring.exceptions;

public class NoBookException extends RuntimeException {
    public NoBookException(Long id) {
        super("No book with id: " + id);
    }
}
