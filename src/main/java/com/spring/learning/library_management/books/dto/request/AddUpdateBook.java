package com.spring.learning.library_management.books.dto.request;

import lombok.Data;

@Data
public class AddUpdateBook {
    private String title;
    private String author;
    private int publicationYear;
    private String genre;

}
