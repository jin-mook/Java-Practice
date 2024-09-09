package com.example.ssedemo.eventlistener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelloEventService {

    private final EventRepository eventRepository;
    private final EventPublisher eventPublisher;

    @Transactional
    public EventEntity saveEvent(String eventName) {

        EventEntity eventEntity = new EventEntity(eventName);
        EventEntity savedEvent = eventRepository.save(eventEntity);

        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("HelloEventService transactionName = {}", transactionName);

        log.info("before event publish");
        eventPublisher.publish(new HelloEventDto(savedEvent));
        log.info("after event publish");

        if (eventName.equals("event2")) {
            throw new RuntimeException("event2 이름입니다.");
        }

        return savedEvent;
    }
}
