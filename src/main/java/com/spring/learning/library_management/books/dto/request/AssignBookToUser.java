package com.spring.learning.library_management.books.dto.request;


import lombok.Data;

@Data
public class AssignBookToUser {
    private Long bookId;
    private Long userId;
    private Integer dueDays;

}
