package com.spring.learning.library_management.user.service;

import com.spring.learning.library_management.user.dto.request.UserByEmail;
import com.spring.learning.library_management.user.entity.User;
import com.spring.learning.library_management.user.repository.UserCustomRepository;
import com.spring.learning.library_management.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;


    @Override
    public List<User> findByEmail(UserByEmail userByEmail) throws Exception {
        return userCustomRepository.findByEmail(userByEmail.getEmail());
    }
}
