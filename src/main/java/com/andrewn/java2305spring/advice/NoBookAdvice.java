package com.andrewn.java2305spring.advice;

import com.andrewn.java2305spring.exceptions.NoBookException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NoBookAdvice {
    @ResponseBody
    @ExceptionHandler(NoBookException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String bookNotFoundHandler(NoBookException ex) {
        return ex.getMessage();
    }
}
