package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Consumer {

    @KafkaListener(topics = Producer.TOPIC)
    public void consumes(String msg) {
        log.info("<<<<<------consumer msg:[{}]", msg);
    }

}