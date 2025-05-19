package com.spring.learning.library_management.books.service;


import com.spring.learning.library_management.books.dto.request.AddUpdateBook;
import com.spring.learning.library_management.books.dto.request.AssignBookToUser;
import com.spring.learning.library_management.books.dto.request.FetchBookByTitle;
import com.spring.learning.library_management.books.entity.Book;
import java.util.List;

public interface IBookService {
    String addBook(AddUpdateBook addUpdateBook);

    List<Book> findByTitle(FetchBookByTitle fetchBookByTitle);

    List<Book> findByGenre(String genre);

    String updateBook(Long id, AddUpdateBook addUpdateBook);

    Book deleteById(Long id) throws Exception;

    String assignBookToUser(AssignBookToUser assignBookToUser);
}
