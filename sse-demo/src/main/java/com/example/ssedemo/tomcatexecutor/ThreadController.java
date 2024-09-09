package com.example.ssedemo.tomcatexecutor;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ThreadController {

    private final ThreadService threadService;

    @GetMapping("/thread")
    public ThreadEntity getThreadInfo() {
        return threadService.getThreadInfo();
    }

}
