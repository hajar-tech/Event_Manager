package com.example.EventManager.configuration;


import com.example.EventManager.entity.User;
import com.example.EventManager.repository.UserReposetory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AdminAuth {

    @Autowired
    private final UserReposetory userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAuth(UserReposetory userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostConstruct
    public void initAdminAuth() {
        String adminUserName = "admin@gmail.com";
        String adminPassword = "admin1234";
        String adminRole = "ADMIN";

        //vérification si l'admin existe déjà
        if (userRepository.findByUsername(adminUserName) == null) {
            User admin = new User();
            admin.setUsername(adminUserName);
            admin.setPassword(passwordEncoder.encode(adminPassword));
            admin.setRole(adminRole);
            admin.setName("admin");
            userRepository.save(admin);
            System.out.println("✅ Admin account created: " + adminUserName);
        } else {
            System.out.println("ℹ️ Admin account already exists.");
        }
    }





}
