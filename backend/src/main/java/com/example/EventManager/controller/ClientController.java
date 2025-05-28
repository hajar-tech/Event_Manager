package com.example.EventManager.controller;

import com.example.EventManager.entity.Reservation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client")
public class ClientController {
    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping("/bonjour")
    public ResponseEntity<String> bonjour() {
        return ResponseEntity.ok("Bonjour in the client dashboard");
    }
//    @PostMapping("/reserver-event")
//    public ResponseEntity<?> reserverEvent(@RequestBody Reservation reservation) {
//        // logique pour réserver un événement
//        return ResponseEntity.ok("Réservation effectuée");
//    }
}
