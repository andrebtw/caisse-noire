package com.boitenoire.ServiceEvent;

import com.boitenoire.Model.Event;

import java.util.List;
import java.util.Optional;

public interface EventService {
    List<Event> findAll();
    Optional<Event> findById(String id);
    Event create(Event event);
}
