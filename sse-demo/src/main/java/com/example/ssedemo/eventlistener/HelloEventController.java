package com.example.ssedemo.eventlistener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Executor;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HelloEventController {

    private final HelloEventService helloEventService;
    private final ThreadPoolTaskExecutor executor;

    @GetMapping("/event/publish")
    public EventEntity eventPublish(@RequestParam("eventName") String eventName) {
        log.info("eventName = {}", eventName);
//        log.info("Current Pool Size: {}", executor.getPoolSize());
//        log.info("active count: {}", executor.getActiveCount());
//        log.info("name prefix: {}", executor.getThreadNamePrefix());
        EventEntity result = helloEventService.saveEvent(eventName);
        log.info("result = {}", result);
        return result;
    }
}
