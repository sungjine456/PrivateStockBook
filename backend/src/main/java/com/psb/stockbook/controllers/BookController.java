package com.psb.stockbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.psb.stockbook.domains.books.BookDto;
import com.psb.stockbook.domains.books.BookEntity;
import com.psb.stockbook.repositories.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BookController {

    @Autowired
    BookRepository repository;

    @GetMapping("/book")
    public List<BookDto> getBook() {
        log.info("get book");

        return repository.findAll().stream().map(s -> s.getDto()).toList();
    }

    @PostMapping("/book")
    public String addBook(@RequestBody BookDto book) {
        log.info("add book, {}", book);

        repository.save(new BookEntity(book));

        return "Success";
    }
}
