package com.spring.learning.library_management.books.repository;

import com.spring.learning.library_management.books.entity.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {
    BookInventory findByBookId(Long bookId);
    // Custom query methods can be defined here if needed
    // For example, findByTitle, findByAuthor, etc.
}
