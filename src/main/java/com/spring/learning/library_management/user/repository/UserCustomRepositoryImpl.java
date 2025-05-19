package com.spring.learning.library_management.user.repository;

import com.spring.learning.library_management.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {
    private EntityManager entityManager;

    @Override
    public List<User> findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.like(root.get("email"), email));
        var query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
