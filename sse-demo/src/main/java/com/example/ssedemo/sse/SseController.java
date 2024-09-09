package com.example.ssedemo.sse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
public class SseController {
    private static final long SSE_TIMEOUT = 10 * 1000L;
    private static final String EVENT_NAME = "article update";
    private static final String NEW_ARTICLE_ALERT_MESSAGE = "new articles are registered";
    private static final String INITIAL_MESSAGE = "sse connected";

    private final ConcurrentHashMap<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    @GetMapping(value = "/api/sse/register", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter register() {
        SseEmitter sseEmitter = new SseEmitter(SSE_TIMEOUT);
        String sseKey = UUID.randomUUID().toString();
        log.info("register sseKey = {}", sseKey);

        emitters.put(sseKey, sseEmitter);

        sseEmitter.onCompletion(() -> {
            log.info("onCompletion sseKey = {}", sseKey);
            emitters.remove(sseKey);
        });

        sseEmitter.onTimeout(sseEmitter::complete);
        return sseEmitter;
    }

    @GetMapping("/api/sse/send")
    public String send() {
        emitters.keySet()
                .forEach(sseKey -> {
                    SseEmitter sseEmitter = emitters.get(sseKey);
                    try {
                        sseEmitter.send(SseEmitter.event().name(EVENT_NAME).data(NEW_ARTICLE_ALERT_MESSAGE));
                    } catch (IOException e) {
                        log.error("sseKey 에러 입니다. = {}", sseKey);
                    }
                });

        return "ok";
    }
}
