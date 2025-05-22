package com.example.EventManager.Repository;

import com.example.EventManager.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepsitory extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
