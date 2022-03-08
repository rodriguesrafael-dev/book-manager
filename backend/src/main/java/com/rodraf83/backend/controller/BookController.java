package com.rodraf83.backend.controller;

import java.util.List;

import javax.validation.Valid;

import com.rodraf83.backend.model.entity.BookEntity;
import com.rodraf83.backend.model.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/books")
@CrossOrigin("http://localhost:4200/")
public class BookController {
    
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookEntity save(@RequestBody @Valid BookEntity bookEntity) {
        return bookRepository.save(bookEntity);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid BookEntity updatedBook) {
        bookRepository.findById(id)
        .map(BookEntity -> {
            updatedBook.setTittle(updatedBook.getTittle());
            updatedBook.setAuthor(updatedBook.getAuthor());          
            return bookRepository.save(updatedBook);
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado!"));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookRepository.findById(id)
        .map(usersEntity -> {
            bookRepository.delete(usersEntity);
            return Void.TYPE;
        })
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado!"));
    }

    @GetMapping("{id}")
    public BookEntity findById(@PathVariable Long id) {
        return bookRepository
        .findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro nao encontrado!"));
    }

    @GetMapping
    public List<BookEntity> getBookList() {
        return bookRepository.findAll();
    }

}
