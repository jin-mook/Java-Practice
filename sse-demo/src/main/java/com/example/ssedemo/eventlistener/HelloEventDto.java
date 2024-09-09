package com.example.ssedemo.eventlistener;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class HelloEventDto {

    private final EventEntity eventEntity;
    private final LocalDateTime eventOccurTime;

    public HelloEventDto(EventEntity eventEntity) {
        this.eventEntity = eventEntity;
        this.eventOccurTime = LocalDateTime.now();
    }
}
