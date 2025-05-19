package com.spring.learning.library_management.books.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "book_inventory")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, updatable = false)
    private Long bookId;

    @Column(name = "total_copies", nullable = false)
    private Integer totalCopies;

    @Column(name = "total_issued", nullable = false)
    private Integer totalIssued;


    @JoinTable(name = "book")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;

}
