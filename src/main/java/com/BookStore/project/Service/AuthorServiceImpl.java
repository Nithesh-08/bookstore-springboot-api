package com.BookStore.project.Service;

import com.BookStore.project.Entity.Author;
import com.BookStore.project.Exception.ResourceNotFoundException;
import com.BookStore.project.Repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService{
    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Author getAuthor(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author author = getAuthor(id);
        author.setName(updatedAuthor.getName());
        return authorRepo.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = getAuthor(id);
        authorRepo.delete(author);
    }
}
