package com.spring.learning.library_management.user.repository;

import com.spring.learning.library_management.user.entity.User;
import java.util.List;


public interface UserCustomRepository {

    List<User> findByEmail(String email);
}
