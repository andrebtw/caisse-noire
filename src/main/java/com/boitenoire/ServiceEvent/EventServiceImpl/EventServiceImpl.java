package com.boitenoire.ServiceEvent.EventServiceImpl;

import com.boitenoire.Model.Event;
import com.boitenoire.RepositoryEvent.RepositoryEvent;
import com.boitenoire.ServiceEvent.EventService;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService {

    private final RepositoryEvent repositoryEvent;
    public EventServiceImpl(RepositoryEvent repositoryEvent) {

        this.repositoryEvent = repositoryEvent;
    }


    @Override
    public List<Event> findAll() {
        return repositoryEvent.findAll();
    }

    @Override
    public Optional<Event> findById(String id) {
        return repositoryEvent.findById(id);
    }

    @Override
    public Event create(Event event) {
        return repositoryEvent.save(event);
    }


}
