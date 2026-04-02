package com.BookStore.project.Service;

import com.BookStore.project.Dto.BookDTO;
import com.BookStore.project.Dto.BookResponseDTO;
import com.BookStore.project.Dto.pagedResponse;
import com.BookStore.project.Entity.Book;
import org.springframework.data.domain.Page;

public interface BookService {
    Book createBook(BookDTO dto);

    BookResponseDTO getBook(Long id);

    Book updateBook(Long id, BookDTO dto);

    pagedResponse<BookResponseDTO> getAll(int page, int size, String sortBy);

    void deleteBook(Long id);
}
