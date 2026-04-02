package com.BookStore.project.Dto;


import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class pagedResponse<T> {

    private List<T> data;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}