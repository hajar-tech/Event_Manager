package com.example.EventManager.mapper;

import com.example.EventManager.dto.EventDto;
import com.example.EventManager.entity.Event;
import com.fasterxml.jackson.databind.deser.UnresolvedId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {



    Event toEntity(EventDto dto);
    EventDto toDTO(Event entity);
}
