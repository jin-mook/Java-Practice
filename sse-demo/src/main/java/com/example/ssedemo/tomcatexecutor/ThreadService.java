package com.example.ssedemo.tomcatexecutor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ThreadService {

    private final ApplicationEventPublisher eventPublisher;
//    private final ThreadPoolTaskExecutor taskExecutor;

    public ThreadEntity getThreadInfo() {
//        log.info("ThreadPool Status - {}", taskExecutor.getThreadNamePrefix());
//        log.info("Core Pool Size: {}", taskExecutor.getCorePoolSize());
//        log.info("Max Pool Size: {}", taskExecutor.getMaxPoolSize());
//        log.info("Active Threads: {}", taskExecutor.getActiveCount());
//        log.info("Pool Size: {}", taskExecutor.getPoolSize());
//        log.info("Queue Size: {}", taskExecutor.getThreadPoolExecutor().getQueue().size());

        for (int i = 0; i < 10; i++) {
            eventPublisher.publishEvent(new ThreadEvent(Thread.currentThread().getName()));
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new ThreadEntity(Thread.currentThread().getName(), Thread.activeCount(), Thread.activeCount());
    }

}
