package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;


@Slf4j
@Service
public class Producer{
    static final String TOPIC = "users";

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        log.info(">>>>>------producer msg: [{}]", msg);
        kafkaTemplate.send(TOPIC, msg);
    }

}