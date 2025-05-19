package com.spring.learning.library_management.user.repository;

import com.spring.learning.library_management.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<User> findByEmail(String email) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        String pattern = "%" + email.trim() + "%";
        query.select(root)
                .where(criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), pattern.toLowerCase()));

        return entityManager.createQuery(query).getResultList();
    }

}
