package com.example.EventManager.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("OK from secured endpoint");
    }

}
