package com.example.ssedemo.eventlistener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;

@Slf4j
@Component
@RequiredArgsConstructor
public class HelloEventListener {

    private final ListenerRepository listenerRepository;
    private final Map<String, Executor> executorMap;

    @Async("customExecutor")
    @EventListener
    public void listen(HelloEventDto helloEventDto) throws InterruptedException {

        Set<String> strings = executorMap.keySet();
        strings.forEach(str -> log.info("str = {}", str));
        log.info("executor keys = {}", strings);

        String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        log.info("HelloEventListener transactionName = {}", transactionName);

        log.info("eventListener start");
        ListenerEntity listenerEntity = ListenerEntity.from(helloEventDto);

        Thread.sleep(5000);

        ListenerEntity savedListenerEntity = listenerRepository.save(listenerEntity);
        log.info("savedListenerEntity = {}", savedListenerEntity);

//        throw new RuntimeException("리스터 에러 발생");
    }
}
