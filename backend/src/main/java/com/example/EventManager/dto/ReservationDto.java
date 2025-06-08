package com.example.EventManager.dto;

public record ReservationDto (

        String userName,
        int numberOfSeats,
        long eventId,
        long userId
) {
}
