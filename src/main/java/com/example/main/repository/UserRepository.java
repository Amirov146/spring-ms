package com.example.main.repository;

import com.example.main.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User save(User user);
    User findUserByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByEmail(String email);
    Optional<User> findUserById(Long id);
}