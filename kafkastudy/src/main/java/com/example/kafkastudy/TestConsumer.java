package com.example.kafkastudy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestConsumer {

//    @KafkaListener(topics = "kafkastudy", groupId = "kafkastudy")
    public void consume(ConsumerRecords<String, String> consumerRecords) {
        log.info("consumerRecords 개수 = {}", consumerRecords.count());
        for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
            log.info("message = {}", consumerRecord.value());
        }
    }

//    @KafkaListener(topics = "kafkastudy", groupId = "kafkastudy")
    public void consume(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) {
        try {
            if (consumerRecord.value().startsWith("3")) {
                throw new RuntimeException("3번째 메시지 입니다.");
            }
            log.info("message = {}, offset = {}", consumerRecord.value(), consumerRecord.offset());
            acknowledgment.acknowledge();
        } catch (Exception e) {

        }
    }

//    @Transactional
    @KafkaListener(topics = "kafkastudy", groupId = "kafkastudy")
    public void consume2(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) throws InterruptedException {
        if (consumerRecord.value().startsWith("3")) {
            throw new RuntimeException("3번째 메시지 입니다.");
        }
        log.info("message = {}, offset = {}", consumerRecord.value(), consumerRecord.offset());
        acknowledgment.acknowledge();
        log.info("동기");
    }
}
