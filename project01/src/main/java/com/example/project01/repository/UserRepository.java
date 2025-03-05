package com.example.project01.repository;

import com.example.project01.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    boolean existsByUsernameAndPassword(String username, String password);

    boolean existsByUsername(String username);

    Optional<UserEntity> findByUsername(String username);

    UserEntity findByUsernameAndPassword(String username, String password);
}
