package com.example.EventManager.service.interfaceServices;

import com.example.EventManager.entity.Event;

import java.util.List;

public interface EventService {

    Event addEvent(Event event);

    List<Event> getEvents();




}
