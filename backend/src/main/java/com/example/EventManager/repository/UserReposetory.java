package com.example.EventManager.repository;


import com.example.EventManager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserReposetory extends JpaRepository<User, Integer> {
    User findByUsername(String username) ;
    @Query
            (value = "select count(u.id), u.role from User u group by u.role")
    public int countRole();
}
