package com.spring.learning.library_management.user.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUser {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String contact;
}
