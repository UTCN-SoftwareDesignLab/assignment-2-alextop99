package com.assignment.assignment2.bookstore.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BookDTO {

    private Long id;

    private String title;

    private String author;

    private String genre;

    private LocalDate publishDate;

    private Long stock;

    private Double price;
}
