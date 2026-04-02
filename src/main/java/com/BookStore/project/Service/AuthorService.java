package com.BookStore.project.Service;

import com.BookStore.project.Entity.Author;

import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);

    List<Author> getAllAuthors();

    Author getAuthor(Long id);

    Author updateAuthor(Long id, Author author);

    void deleteAuthor(Long id);
}
