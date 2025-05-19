package com.spring.learning.library_management.user.service;

import com.spring.learning.library_management.user.dto.request.CreateUser;
import com.spring.learning.library_management.user.dto.request.UserByEmail;
import com.spring.learning.library_management.user.entity.User;
import com.spring.learning.library_management.user.repository.UserCustomRepository;
import com.spring.learning.library_management.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserCustomRepository userCustomRepository;


    @Override
    public List<User> findByEmail(UserByEmail userByEmail) throws Exception {
        return userCustomRepository.findByEmail(userByEmail.getEmail());
    }

    @Override
    public String addUser(CreateUser createUser) throws Exception {
        List<User> existingUsers = userCustomRepository.findByEmail(createUser.getEmail());
        if (Objects.nonNull(existingUsers) && !existingUsers.isEmpty()) {
                throw new Exception("User already exists with email: " + createUser.getEmail());
        }
        User user = new User();
        user.setEmail(createUser.getEmail());
        user.setFirstName(createUser.getFirstName());
        user.setLastName(createUser.getLastName());
        user.setContact(createUser.getContact());

        userRepository.save(user);
        return "User created successfully with email: " + createUser.getEmail();
    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        if(Objects.nonNull(user)) {
            user.setStatus(false);
            userRepository.save(user);
        }
    }
}
