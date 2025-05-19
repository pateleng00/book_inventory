package com.spring.learning.library_management.books.dto.request;


import lombok.Data;
import lombok.NonNull;

@Data
public class FetchByGenre {

    @NonNull
    private String genre;

}
