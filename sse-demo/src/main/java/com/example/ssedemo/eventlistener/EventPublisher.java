package com.example.ssedemo.eventlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    public EventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publish(HelloEventDto helloEventDto) {
        applicationEventPublisher.publishEvent(helloEventDto);
    }
}
