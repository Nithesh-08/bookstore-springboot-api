package com.BookStore.project.Controller;

import com.BookStore.project.Dto.BookDTO;
import com.BookStore.project.Dto.BookResponseDTO;
import com.BookStore.project.Dto.pagedResponse;
import com.BookStore.project.Dto.pagedResponse;
import com.BookStore.project.Entity.Book;
import com.BookStore.project.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody BookDTO dto) {
        return new ResponseEntity<>(service.createBook(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getBook(id));
    }

    @GetMapping
    public ResponseEntity<pagedResponse<BookResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(service.getAll(page, size, sortBy));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> update(@PathVariable Long id,
                                       @RequestBody BookDTO dto) {
        return ResponseEntity.ok(service.updateBook(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
