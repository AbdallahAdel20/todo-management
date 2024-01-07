package com.example.todomanagement.repo;

import com.example.todomanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);
    Optional<User> findByUsernameOrEmail(String email , String username);

}
