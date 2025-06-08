package com.example.EventManager.dto;

public record ReservationDto (
        Long id,
        String userName,
        int numberOfSeats,
        long eventId,
        long userId
) {
}
