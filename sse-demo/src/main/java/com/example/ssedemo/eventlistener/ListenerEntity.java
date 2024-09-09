package com.example.ssedemo.eventlistener;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ListenerEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String eventName;

    public ListenerEntity(String eventName) {
        this.eventName = eventName;
    }

    public static ListenerEntity from(HelloEventDto helloEventDto) {

        return new ListenerEntity(helloEventDto.getEventEntity().getEventName() + " listener entity");
    }
}
