package com.example.main.serviceImpl;

import com.example.main.model.User;
import com.example.main.repository.UserRepository;
import com.example.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {


    private String encoder(String password){
        return new BCryptPasswordEncoder().encode(password);
    }

    @Autowired
    public UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setPassword(encoder(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsername(String login) {
        return userRepository.findUserByUsername(login);
    }

    @Override
    public boolean existsUserByUsername(String login) {
        return userRepository.existsUserByUsername(login);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }
}

