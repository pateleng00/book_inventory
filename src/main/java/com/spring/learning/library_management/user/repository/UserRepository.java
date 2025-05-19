package com.spring.learning.library_management.user.repository;

import com.spring.learning.library_management.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByUsername, findByEmail, etc.
}
