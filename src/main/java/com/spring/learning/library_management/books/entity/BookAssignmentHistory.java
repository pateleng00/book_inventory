package com.spring.learning.library_management.books.entity;


import com.spring.learning.library_management.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "book_assignment_history")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookAssignmentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;


    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "return_date")
    private String returnDate;

    @Column(name = "due_days", nullable = false)
    private Integer dueDays;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book bookDetails;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User userDetails;
}
