package com.example.project01.repository;

import com.example.project01.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);
}
