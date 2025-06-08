package com.example.EventManager.mapper;

import com.example.EventManager.dto.ReservationDto;
import com.example.EventManager.entity.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationDto toDTO(Reservation reservation);
    Reservation toEntity(ReservationDto dto);
}
