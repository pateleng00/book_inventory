package com.spring.learning.library_management.books.controller;


import com.spring.learning.library_management.books.dto.request.AddUpdateBook;
import com.spring.learning.library_management.books.dto.request.AssignBookToUser;
import com.spring.learning.library_management.books.dto.request.FetchBookByTitle;
import com.spring.learning.library_management.books.repository.BookRepository;
import com.spring.learning.library_management.books.service.IBookService;
import com.spring.learning.library_management.common.dto.RestApiResponse;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BooksController {

    private final BookRepository bookRepository;
    private final IBookService bookService;


    @GetMapping("/fetch-all")
    public ResponseEntity<RestApiResponse<Object>> getAllBooks() {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookRepository.findAll()));
    }


    @GetMapping("/fetch-by-id/{id}")
    public ResponseEntity<RestApiResponse<Object>> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id))));

    }


    @PostMapping("/add-book")
    public ResponseEntity<RestApiResponse<Object>> addBook(@RequestBody AddUpdateBook addUpdateBook) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookService.addBook(addUpdateBook)));
    }


    @DeleteMapping("/delete-book/{name}")
    public ResponseEntity<RestApiResponse<Object>> deleteBook(@PathVariable String name) {
        bookRepository.deleteByTitle(name);
        return ResponseEntity.ok(RestApiResponse.buildSuccess("Book deleted successfully"));
    }

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<RestApiResponse<Object>> deleteBookById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookService.deleteById(id)));
    }


    @PostMapping("/fetch-by-title")
    public ResponseEntity<RestApiResponse<Object>> getBookByTitle(@RequestBody FetchBookByTitle fetchBookByTitle) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookService.findByTitle(fetchBookByTitle)));
    }


    @GetMapping("/fetch-by-genre/{genre}")
    public ResponseEntity<RestApiResponse<Object>> getBookByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookService.findByGenre(genre)));
    }


    @PutMapping("/update-book/{id}")
    public ResponseEntity<RestApiResponse<Object>> updateBook(@PathVariable Long id, @RequestBody AddUpdateBook addUpdateBook) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookService.updateBook(id, addUpdateBook)));
    }


    @PostMapping("/assign-book-to-user")
    public ResponseEntity<RestApiResponse<Object>> assignBookToUser(@RequestBody AssignBookToUser assignBookToUser) {
        return ResponseEntity.ok(RestApiResponse.buildSuccess(bookService.assignBookToUser(assignBookToUser)));
    }




}