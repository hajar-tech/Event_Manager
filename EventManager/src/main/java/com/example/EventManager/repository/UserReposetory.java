package com.example.EventManager.repository;


import com.example.EventManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposetory extends JpaRepository<User, Integer> {
    User findByUsername(String username) ;
}
