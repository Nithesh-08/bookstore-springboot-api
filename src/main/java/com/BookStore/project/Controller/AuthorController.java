package com.BookStore.project.Controller;

import com.BookStore.project.Entity.Author;
import com.BookStore.project.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @PostMapping
    public Author create(@RequestBody Author author) {
        return service.save(author);
    }

    @GetMapping
    public List<Author> getAll() {
        return service.findAll();
    }
}
