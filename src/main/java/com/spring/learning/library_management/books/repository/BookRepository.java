package com.spring.learning.library_management.books.repository;

import com.spring.learning.library_management.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = """
            SELECT * FROM book
            WHERE title like CONCAT('%', :title, '%')
            """, nativeQuery = true)
    List<Book> findByTitle(String title);



    @Query(value = """
            SELECT * FROM book
            WHERE genre like CONCAT('%', :genre, '%')
            """, nativeQuery = true)
    List<Book> findByGenre(String genre);

    void deleteByTitle(String name);
}
