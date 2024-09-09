package com.example.kafkastudy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private static final String topic = "kafkastudy";

    private final KafkaTemplate<String, String> kafkaTemplate;
    private int count = 1;

    @Transactional
    @GetMapping("one-record")
    public String oneRecord() {
        String message = count + "번째 메시지 입니다.";
        kafkaTemplate.send(topic, message);
        count++;
        return message;
    }

    @GetMapping("count-record")
    public String countRecord() {
        String message = count + "만큼의 동일한 메시지를 보냅니다.";
        for (int i = 0; i < 100; i++) {
            kafkaTemplate.send(topic, message);
        }
        return message;
    }

    @Transactional
    @GetMapping("transaction")
    public String transactionRecord() {
        System.out.println("트랜잭션 테스트입니다.");
        String message = count + "번째 트랜잭션 메시지 입니다.";
//        kafkaTemplate.executeInTransaction(template -> {
//            CompletableFuture<SendResult<String, String>> result = template.send(topic, message);
//            return null;
//        });
        kafkaTemplate.send(topic, message);
        count++;
        return message;
    }

}
