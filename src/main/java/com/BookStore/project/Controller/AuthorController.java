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
    private AuthorRepository repo;

    @PostMapping
    public Author create(@RequestBody Author author) {
        return repo.save(author);
    }

    @GetMapping
    public List<Author> getAll() {
        return repo.findAll();
    }
}
