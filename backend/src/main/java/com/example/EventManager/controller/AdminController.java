package com.example.EventManager.controller;

import com.example.EventManager.dto.EventDto;
import com.example.EventManager.entity.Event;
import com.example.EventManager.mapper.EventMapper;
import com.example.EventManager.repository.EventRepository;
import com.example.EventManager.service.implementationServices.EventImpl;
import com.example.EventManager.service.interfaceServices.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final EventImpl eventImpl;

    @Autowired
    public AdminController(EventRepository eventRepository, EventMapper eventMapper , EventImpl eventImpl) {
        this.eventRepository = eventRepository;
        this.eventMapper = eventMapper;
        this.eventImpl = eventImpl;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/bonjour")
    public ResponseEntity<String> bonjour() {
        return ResponseEntity.ok("Bonjour in the admin dashboard");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create-event")
    public ResponseEntity<EventDto> createEvent(@RequestBody EventDto eventDTO) {
        Event saved = eventRepository.save(eventMapper.toEntity(eventDTO));
        return ResponseEntity.ok(eventMapper.toDTO(saved));
    }

    @GetMapping("/getEvents")
    public ResponseEntity<List<Event>> getEvents() {
        List<Event> events = eventImpl.getEvents();
        return ResponseEntity.ok(events);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")

    public Event addEvent(@RequestBody Event event){
        return eventImpl.addEvent(event);
    }

//    @PostMapping("/create-event")
//    public ResponseEntity<?> createEvent(@RequestBody Event event) {
//        // logique pour créer un événement
//        return ResponseEntity.ok("Événement créé avec succès");
//    }
}
