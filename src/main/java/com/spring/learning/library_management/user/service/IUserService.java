package com.spring.learning.library_management.user.service;

import com.spring.learning.library_management.user.dto.request.UserByEmail;
import com.spring.learning.library_management.user.entity.User;

import java.util.List;

public interface IUserService {

    List<User> findByEmail(UserByEmail userByEmail) throws Exception;
}
