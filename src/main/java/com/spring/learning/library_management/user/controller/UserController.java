package com.spring.learning.library_management.user.controller;


import com.spring.learning.library_management.common.dto.RestApiResponse;
import com.spring.learning.library_management.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/fetch-all")
    public ResponseEntity<RestApiResponse<Object>> getAllUsers() {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userRepository.findAll()));
    }
}
