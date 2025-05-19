package com.spring.learning.library_management.books.repository;

import com.spring.learning.library_management.books.entity.Book;


import java.util.List;

/**
 * BookCustomRepository is a custom repository interface for performing complex queries on the Book entity.
 * It extends the JpaRepository interface to inherit basic CRUD operations.
 */
public interface BookCustomRepository {

    List<Book> findByTitle(String title);


    List<Book> findByGenre(String genre);
}
