package com.example.EventManager.service.implementationServices;

import com.example.EventManager.entity.Event;
import com.example.EventManager.repository.EventRepository;
import com.example.EventManager.service.interfaceServices.EventService;
import org.springframework.stereotype.Service;

@Service
public class EventImpl implements EventService {

    EventRepository eventRepository;
    public EventImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event addEvent(Event event) {
     return   eventRepository.save(event);
    }
}
