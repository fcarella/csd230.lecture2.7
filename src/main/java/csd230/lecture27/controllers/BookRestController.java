package csd230.lecture27.controllers;

import csd230.lecture27.entities.Book;
import csd230.lecture27.respositories.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRestController {
    BookRepository bookRepository;
    public BookRestController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/getRestBooks")
    List<Book> getRestBooks(){
        List<Book> books = bookRepository.findAll();
        return books;
    }

}
