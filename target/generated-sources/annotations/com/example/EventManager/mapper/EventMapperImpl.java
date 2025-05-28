package com.example.EventManager.mapper;

import com.example.EventManager.dto.EventDto;
import com.example.EventManager.entity.Event;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-28T15:11:23+0100",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class EventMapperImpl implements EventMapper {

    @Override
    public Event toEntity(EventDto dto) {
        if ( dto == null ) {
            return null;
        }

        Event event = new Event();

        return event;
    }

    @Override
    public EventDto toDTO(Event entity) {
        if ( entity == null ) {
            return null;
        }

        long id = 0L;
        String titre = null;
        String description = null;
        LocalDate date = null;
        int placesDisponibles = 0;
        String category = null;
        String location = null;

        EventDto eventDto = new EventDto( id, titre, description, date, placesDisponibles, category, location );

        return eventDto;
    }
}
