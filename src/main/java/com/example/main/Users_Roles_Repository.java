package com.example.main;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Users_Roles_Repository extends JpaRepository<Users_Roles,Long> {
    Users_Roles save(Users_Roles users_roles);
}
