package com.spring.learning.library_management.books.repository;

import com.spring.learning.library_management.books.entity.BookAssignmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAssignmentHistoryRepository extends JpaRepository<BookAssignmentHistory, Long> {
    // Custom query methods can be defined here if needed
    // For example, findByTitle, findByAuthor, etc.
}
