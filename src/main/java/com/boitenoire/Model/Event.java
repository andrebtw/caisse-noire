package com.boitenoire.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.service.invoker.HttpRequestValues;

import java.time.LocalDateTime;

@Document(collection = "event")
public class Event {
    @Id
    private String id;

    private String eventType;

    private LocalDateTime timestamp;

    private String userId;

    private String source;

    private Metadata metadata;

    private Object data;

    public Event() {
    }

    public Event(String id, String eventType, LocalDateTime timestamp, String userId, String source, HttpRequestValues.Metadata metadata, Object data) {
        this.id = id;
        this.eventType = eventType;
        this.timestamp = timestamp;
        this.userId = userId;
        this.source = source;
       // this.metadata = metadata;
        this.data = data;
    }

    public String getId() {
        return id; }
    public void setId(String id) {
        this.id = id; }

    public String getEventType() {
        return eventType; }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }
}