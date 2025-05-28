package com.example.EventManager.controller;

import com.example.EventManager.dto.EventDto;
import com.example.EventManager.entity.Event;
import com.example.EventManager.mapper.EventMapper;
import com.example.EventManager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

//
//    private final EventRepository eventRepository;
//   private final EventMapper eventMapper;
//
//   @Autowired
//   public EventController(EventRepository eventRepository, EventMapper eventMapper) {
//       this.eventRepository = eventRepository;
//       this.eventMapper = eventMapper;
//   }
//
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/create-event")
//    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDTO) {
//        Event saved = eventRepository.save(eventMapper.toEntity(eventDTO));
//        return ResponseEntity.ok(eventMapper.toDTO(saved));
//    }
}
