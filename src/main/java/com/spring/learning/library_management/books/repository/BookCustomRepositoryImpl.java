package com.spring.learning.library_management.books.repository;

import com.spring.learning.library_management.books.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * BookCustomRepositoryImpl is an implementation of the BookCustomRepository interface.
 * It provides custom query methods for the Book entity.
 * This class is responsible for executing complex queries that are not covered by the default CRUD operations.
 */

@Repository
public class BookCustomRepositoryImpl implements BookCustomRepository{
    // EntityManager is used to interact with the persistence context

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Book> findByTitle(String title) {

        CriteriaBuilder cb  = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> root = query.from(Book.class);
        String pattern = "%" + title.trim() + "%";
        query.select(root)
                .where(cb.like(cb.lower(root.get("title")), pattern.toLowerCase()));

        TypedQuery<Book> queryResult = entityManager.createQuery(query);

        List<Book> results = queryResult.getResultList();
        results.forEach(System.out::println);
        return results;
    }

    @Override
    public List<Book> findByGenre(String genre) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = cb.createQuery(Book.class);
        Root<Book> root = criteriaQuery.from(Book.class);

        String pattern = "%" + genre.trim() + "%";
        criteriaQuery.select(root)
                .where(cb.like(cb.lower(root.get("genre")), pattern.toLowerCase()));

        TypedQuery<Book> queryResult = entityManager.createQuery(criteriaQuery);

        List<Book> results = queryResult.getResultList();
        results.forEach(System.out::println);
        return results;
    }
}
