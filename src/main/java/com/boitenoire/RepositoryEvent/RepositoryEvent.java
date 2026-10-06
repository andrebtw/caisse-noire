package com.boitenoire.RepositoryEvent;

import com.boitenoire.Model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositoryEvent extends MongoRepository<Event, String> {

}
