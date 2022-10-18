package com.andrewn.java2305spring.controller;

import com.andrewn.java2305spring.exceptions.NoBookException;
import com.andrewn.java2305spring.model.Book;
import com.andrewn.java2305spring.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

//    @GetMapping("/book")
//    Book findBook(@RequestParam(name="id") String idToFind) {
//        Optional<Book> bookBox = bookRepository.findById(Long.parseLong(idToFind));
//        if (bookBox.isPresent()) {
//            return bookBox.get();
//        } else {
//            return null;
//        }
//    }

//    @GetMapping("/create-book")
//    Book createBook(@RequestParam(name="name") String bookName,
//                    @RequestParam(name="year", required=false, defaultValue="0") String publishYear) {
//        return bookRepository.save(new Book(bookName, Integer.parseInt(publishYear)));
//    }
//
//    @GetMapping("/delete-book")
//    void deleteBook(@RequestParam(name="id") String idToDelete) {
//        bookRepository.deleteById(Long.parseLong(idToDelete));
//    }
//
//    @GetMapping("/change-book")
//    Book changeBook(@RequestParam(name="id") String idToChange,
//                    @RequestParam(name="name", required = false, defaultValue = "") String newName,
//                    @RequestParam(name="year", required = false, defaultValue = "") String newYear) {
//        Optional<Book> bookBox = bookRepository.findById(Long.parseLong(idToChange));
//        if (bookBox.isPresent()) {
//            Book bookToChange = bookBox.get();
//            bookToChange.setName(newName);
//            bookToChange.setPublishYear(Integer.parseInt(newYear));
//            return bookRepository.save(bookToChange);
//        } else {
//            return null;
//        }
//    }

    @PostMapping("/books")
    Book createBook(@RequestBody Book newBook) {
        return bookRepository.save(newBook);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/books/{id}")
    Book findBook(@PathVariable Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NoBookException(id));

    }

    @PutMapping("/books/{id}")
    Book changeBook(@PathVariable Long id,
                    @RequestBody Book changedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setName(changedBook.getName());
                    book.setPublishYear(changedBook.getPublishYear());
                    return bookRepository.save(book);
                }).orElseGet(() -> bookRepository.save(changedBook));
    }
}
