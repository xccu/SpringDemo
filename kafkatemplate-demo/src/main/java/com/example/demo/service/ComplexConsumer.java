package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ComplexConsumer {

    @KafkaListener(topics = "${spring.kafka.topic}")
    public void consumesByConfig(String msg) {
        log.info("<<<<<------consumer2 msg:[{}]", msg);
    }
}
