package com.andrewn.java2305spring.controller;

import com.andrewn.java2305spring.exceptions.NoBookException;
import com.andrewn.java2305spring.model.Book;
import com.andrewn.java2305spring.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    // Создать класс Employee из трех полей Long id, String name, String role
    // Добавить аннотации, чтобы существовала таблица employee с этими столбцами
    // Создать интерфейс EmployeeRepository, который наследуется от CrudRepository
    // добавить в него метод, который с помощью ключевых слов find, By и имени
    // столбца ищет всех сотрудников по роли
    // Создать EmployeeController, который является @RestController и содержит два метода
    // getEmployees, возвращающий список всех сотрудников
    // getEmployeesWithRole, возвращающий список сотрудников определенной роли (роль взять из @RequestParam)

    private final BookRepository bookRepository;
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @GetMapping("/books")
    Iterable<Book> allBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/name")
    List<Book> bookByName(@RequestParam(name="name") String name) {
        return bookRepository.filterByName(name);
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
    // книги после конкретного года (RequestHeader + репозиторий)

    @GetMapping("/books/after-year")
    List<Book> booksAfterYear(@RequestParam(name="after") int year) {
        return bookRepository.findByPublishYearGreaterThan(year);
    }

    @GetMapping("/books/between")
    List<Book> booksBetween(@RequestHeader(name="before") int before,
                            @RequestHeader(name="after") int after) {
        return bookRepository.findByPublishYearBetweenOrderByPublishYearDescNameAsc(after, before);
    }
}
