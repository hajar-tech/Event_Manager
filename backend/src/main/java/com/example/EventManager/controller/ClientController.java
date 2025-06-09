package com.example.EventManager.controller;

import com.example.EventManager.dto.ReservationDto;
import com.example.EventManager.entity.Reservation;
import com.example.EventManager.mapper.ReservationMapper;
import com.example.EventManager.service.implementationServices.ReservationImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ReservationImpl reservationImpl;
    private final ReservationMapper reservationMapper;

    public ClientController(ReservationImpl reservationImpl, ReservationMapper reservationMapper) {
        this.reservationImpl = reservationImpl;
        this.reservationMapper = reservationMapper;
    }

    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
    @GetMapping("/bonjour")
    public ResponseEntity<String> bonjour() {
        return ResponseEntity.ok("Bonjour in the client dashboard");
    }


    @PreAuthorize("hasAnyRole('CLIENT', 'ADMIN')")
   @PostMapping("/reserver-event")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservationdto) {
        Reservation reservation = reservationMapper.toReservation(reservationdto);
        Reservation saved = reservationImpl.addReservation(reservation);
        return ResponseEntity.ok(reservationMapper.toDto(saved));
   }

//    public ResponseEntity<?> reserverEvent(@RequestBody Reservation reservation) {
//        // logique pour réserver un événement
//        return ResponseEntity.ok("Réservation effectuée");
//    }
}
