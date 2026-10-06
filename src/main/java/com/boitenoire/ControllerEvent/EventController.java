package com.boitenoire.ControllerEvent;

import com.boitenoire.Model.Event;
import com.boitenoire.ServiceEvent.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> all() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> get(@PathVariable String id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event newEvent) {

        Event ev = new Event();

        ev.setId(newEvent.getId());
        ev.setEventType(newEvent.getEventType());
        ev.setData(newEvent.getData());
        ev.setMetadata(newEvent.getMetadata());
        ev.setUserId(newEvent.getUserId());
        ev.setSource(newEvent.getSource());

        Event create = eventService.create(ev);

        return ResponseEntity
                .created(URI.create("/boite-noire/events/" + create.getId()))
                .body(create);
    }
}