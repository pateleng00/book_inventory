package com.spring.learning.library_management.user.service;

import com.spring.learning.library_management.user.entity.User;

import java.util.List;

public interface IUserService {

    List<User> findByEmail(String email);
}
