package com.andrewn.java2305spring.repository;

import com.andrewn.java2305spring.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository
        extends CrudRepository<Book, Long> {
}
