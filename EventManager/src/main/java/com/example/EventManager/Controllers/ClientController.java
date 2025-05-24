package com.example.EventManager.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client")


public class ClientController {
    @GetMapping
    public ResponseEntity<String> getClientController() {
        return ResponseEntity.ok("Hello from Client Controller");
    }
}
