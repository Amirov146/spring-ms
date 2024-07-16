package com.example.main.service;

import com.example.main.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();
    User save(User user);
    User findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    Optional<User> findUserById(Long id);

}
