package com.BookStore.project.Service;

import com.BookStore.project.Dto.BookDTO;
import com.BookStore.project.Dto.BookResponseDTO;
import com.BookStore.project.Dto.pagedResponse;
import com.BookStore.project.Dto.pagedResponse;
import com.BookStore.project.Entity.Author;
import com.BookStore.project.Entity.Book;
import com.BookStore.project.Exception.ResourceNotFoundException;
import com.BookStore.project.Repository.AuthorRepository;
import com.BookStore.project.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Override
    public Book createBook(BookDTO dto) {

        Author author = authorRepo.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setAuthor(author);
        book.setPrice(dto.getPrice());

        return bookRepo.save(book);
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book=bookRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Book not Found"));
        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getAuthor().getName(),
                book.getPrice());
    }

    @Override
    public Book updateBook(Long id, BookDTO dto) {

        Book book = bookRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book not found"));

        Author author = authorRepo.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));

        book.setTitle(dto.getTitle());
        book.setAuthor(author);
        book.setPrice(dto.getPrice());

        return bookRepo.save(book);
    }

    @Override
    public pagedResponse<BookResponseDTO> getAll(int page, int size, String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Book> bookPage = bookRepo.findAll(pageable);

        List<BookResponseDTO> list = bookPage.getContent().stream()
                .map(book -> new BookResponseDTO(
                        book.getId(),
                        book.getTitle(),
                        book.getAuthor().getName(),
                        book.getPrice()
                ))
                .toList();

        return new pagedResponse<>(
                list,
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalElements(),
                bookPage.getTotalPages()
        );
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Book not Found"));
        bookRepo.delete(book);
    }
}
