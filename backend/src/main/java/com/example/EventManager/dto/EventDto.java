package com.example.EventManager.dto;

import com.example.EventManager.entity.Reservation;

import java.time.LocalDate;
import java.util.List;

public record EventDto (
        Long id,
        String titre,
        String description,
        LocalDate date,
        int placesDisponibles,
        String category,
        String location,
        List<ReservationDto> reservations
        ){


}
