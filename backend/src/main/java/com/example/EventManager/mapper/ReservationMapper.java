package com.example.EventManager.mapper;

import com.example.EventManager.dto.ReservationDto;
import com.example.EventManager.entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "user.id", target = "userId")
    ReservationDto toDto(Reservation reservation);

    @Mapping(target = "event.id", source = "eventId")
    @Mapping(target = "user.id", source = "userId")
    Reservation toReservation(ReservationDto dto);
}
