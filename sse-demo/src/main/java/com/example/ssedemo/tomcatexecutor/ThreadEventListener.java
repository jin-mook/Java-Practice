package com.example.ssedemo.tomcatexecutor;

import com.example.ssedemo.eventlistener.HelloEventDto;
import com.example.ssedemo.eventlistener.ListenerEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j
@Component
@RequiredArgsConstructor
public class ThreadEventListener {

//    private final ThreadPoolTaskExecutor taskExecutor;

    @Async
    @EventListener
    public void listen(HelloEventDto helloEventDto) throws InterruptedException {

//        log.info("ThreadPool Status - {}", taskExecutor.getThreadNamePrefix());
//        log.info("Core Pool Size: {}", taskExecutor.getCorePoolSize());
//        log.info("Max Pool Size: {}", taskExecutor.getMaxPoolSize());
//        log.info("Active Threads: {}", taskExecutor.getActiveCount());
//        log.info("Pool Size: {}", taskExecutor.getPoolSize());
//        log.info("Queue Size: {}", taskExecutor.getThreadPoolExecutor().getQueue().size());

        Thread.sleep(5000);
    }

}
