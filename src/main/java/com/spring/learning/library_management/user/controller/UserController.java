package com.spring.learning.library_management.user.controller;


import com.spring.learning.library_management.common.dto.RestApiResponse;
import com.spring.learning.library_management.user.repository.UserCustomRepository;
import com.spring.learning.library_management.user.repository.UserRepository;
import com.spring.learning.library_management.user.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;
    private final IUserService userService;


    @GetMapping("/fetch-all")
    public ResponseEntity<RestApiResponse<Object>> getAllUsers() {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userRepository.findAll()));
    }

    @GetMapping("/fetch-by-id/{id}")
    public ResponseEntity<RestApiResponse<Object>> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id))));
    }

    @GetMapping("/fetch-by-email/{email}")
    public ResponseEntity<RestApiResponse<Object>> getUserByEmail(@PathVariable String email) throws Exception {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(userService.findByEmail(email)));
    }

}
