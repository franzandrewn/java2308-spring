package com.andrewn.java2305spring.repository;

import com.andrewn.java2305spring.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository
        extends JpaRepository<Book, Long> {

    List<Book> findByName(String name);

    List<Book> findByPublishYearGreaterThan(int year);

    List<Book> findByPublishYearBetweenOrderByPublishYearDescNameAsc(int after, int before);

    @Query("select b from Book b where b.name = ?1")
    List<Book> filterByName(String name);
}
