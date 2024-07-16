package com.example.main.repository;

import com.example.main.model.UsersRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRolesRepository extends JpaRepository<UsersRoles,Long> {
    UsersRoles save(UsersRoles users_roles);
}
