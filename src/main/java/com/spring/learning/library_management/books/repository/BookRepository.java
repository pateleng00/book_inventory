package com.spring.learning.library_management.books.repository;

import com.spring.learning.library_management.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BookRepository extends JpaRepository<Book, Long> {



    void deleteByTitle(String name);
}
