package com.BookStore.project.Dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private String title;
    private Long authorId;
    private double price;
}
