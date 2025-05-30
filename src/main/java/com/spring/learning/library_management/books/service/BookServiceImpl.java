package com.spring.learning.library_management.books.service;

import com.spring.learning.library_management.books.dto.request.AddUpdateBook;
import com.spring.learning.library_management.books.dto.request.AssignBookToUser;
import com.spring.learning.library_management.books.dto.request.FetchBookByTitle;
import com.spring.learning.library_management.books.dto.request.FetchByGenre;
import com.spring.learning.library_management.books.entity.Book;
import com.spring.learning.library_management.books.entity.BookAssignmentHistory;
import com.spring.learning.library_management.books.entity.BookInventory;
import com.spring.learning.library_management.books.repository.BookAssignmentHistoryRepository;
import com.spring.learning.library_management.books.repository.BookCustomRepository;
import com.spring.learning.library_management.books.repository.BookInventoryRepository;
import com.spring.learning.library_management.books.repository.BookRepository;
import com.spring.learning.library_management.user.repository.UserRepository;
import com.spring.learning.library_management.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;


/**
 * BookServiceImpl is a service class that handles the business logic for managing books in the library.
 * It provides methods to add, fetch, and delete books.
 */
@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements IBookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookAssignmentHistoryRepository bookAssignmentHistoryRepository;
    private final BookInventoryRepository bookInventoryRepository;
    private final BookCustomRepository bookCustomRepository;


    public String addBook(AddUpdateBook addUpdateBook) {

        if (addUpdateBook.getTitle() == null || addUpdateBook.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (addUpdateBook.getAuthor() == null || addUpdateBook.getAuthor().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        if (addUpdateBook.getPublicationYear() <= 0) {
            throw new IllegalArgumentException("Publication year must be a positive number");
        }
        if (addUpdateBook.getGenre() == null || addUpdateBook.getGenre().isEmpty()) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }

        Book book = new Book();
        book.setTitle(addUpdateBook.getTitle());
        book.setAuthor(addUpdateBook.getAuthor());
        book.setPublicationYear(addUpdateBook.getPublicationYear());
        book.setGenre(addUpdateBook.getGenre());
        bookRepository.save(book);

        return "Book added successfully";
    }

    @Override
    public List<Book> findByTitle(FetchBookByTitle fetchBookByTitle) {
        log.info("Fetching book by title: {}", fetchBookByTitle.getTitle());
        if ( fetchBookByTitle.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        List<Book> books = bookCustomRepository.findByTitle(fetchBookByTitle.getTitle());
        return books.isEmpty() ? List.of() : books;
    }

    @Override
    public List<Book> findByGenre(FetchByGenre fetchByGenre) {
        if (Objects.isNull(fetchByGenre)) {
            throw new IllegalArgumentException("Genre cannot be null or empty");
        }
        return bookCustomRepository.findByGenre(fetchByGenre.getGenre());
    }

    @Override
    public String updateBook(Long id, AddUpdateBook addUpdateBook) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        if (addUpdateBook.getTitle() != null && !addUpdateBook.getTitle().isEmpty()) {
            book.setTitle(addUpdateBook.getTitle());
        }
        if (addUpdateBook.getAuthor() != null && !addUpdateBook.getAuthor().isEmpty()) {
            book.setAuthor(addUpdateBook.getAuthor());
        }
        if ( addUpdateBook.getPublicationYear() > 0) {
            book.setPublicationYear(addUpdateBook.getPublicationYear());
        }
        if (addUpdateBook.getGenre() != null && !addUpdateBook.getGenre().isEmpty()) {
            book.setGenre(addUpdateBook.getGenre());
        }

        bookRepository.save(book);
        return "Book updated successfully";
    }

    @Override
    public Book deleteById(Long id) throws Exception {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new Exception("Book not found with id: " + id));
        bookRepository.delete(book);
        log.info("Book with id {} and title {} deleted successfully", id, book.getTitle());
        return book;
    }

    @Override
    public String assignBookToUser(AssignBookToUser assignBookToUser) {
        Book book = bookRepository.findById(assignBookToUser.getBookId())
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + assignBookToUser.getBookId()));
        User user = userRepository.findById(assignBookToUser.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + assignBookToUser.getUserId()));


        BookInventory bookInventorySearch = bookInventoryRepository.findByBookId(book.getBookId());

        if(bookInventorySearch == null) {
            throw new RuntimeException("Book not found in inventory");
        }

        if (bookInventorySearch.getTotalCopies() - bookInventorySearch.getTotalIssued() == 0) {
            throw new RuntimeException("No available copies of the book");
        }

        bookInventorySearch.setTotalIssued(bookInventorySearch.getTotalIssued() + 1);

        if(Objects.nonNull(user)) {
            BookAssignmentHistory bookAssignmentHistory = new BookAssignmentHistory();
            bookAssignmentHistory.setUserId(user.getUserId());
            bookAssignmentHistory.setBookId(book.getBookId());
            bookAssignmentHistory.setDueDays(assignBookToUser.getDueDays());
            bookAssignmentHistoryRepository.save(bookAssignmentHistory);

        }

        bookInventoryRepository.save(bookInventorySearch);


        return "Book with title " + book.getTitle() + " assigned to user "+ user.getUserId() + " successfully";
    }

    @Override
    public Optional<Book> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Book ID cannot be null");
        }
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book;
        } else {
            throw new RuntimeException("Book not found with id: " + id);
        }
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteByTitle(String name) throws Exception {
        List<Book> books = bookCustomRepository.findByTitle(name);
        if(Objects.isNull(books) || books.isEmpty()) {
            throw new Exception("Book not found with title: " + name);
        }
        if (books.size() > 1) {
            throw new Exception("Multiple books found with title: " + name + ". Total books are: " + books.size());

        }

        log.info("Deleting book with matching title: {}", name);
        List<Book> booksToUpdate = new ArrayList<>();
        for(Book book : books) {
            book.setStatus(false);
            booksToUpdate.add(book);
        }
        bookRepository.saveAll(booksToUpdate);
        log.info("Book with matching title {} deleted successfully", name);
    }


}
