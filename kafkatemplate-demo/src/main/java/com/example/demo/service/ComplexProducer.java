package com.example.demo.service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ComplexProducer {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${spring.kafka.topic}")
    private String topic;

    public boolean sendMessageSync(String key, String msg) {
        log.info(">>>>>------producer msg: [{}]", msg);

        final boolean[] ok = {true};
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, msg);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(record);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(@NonNull Throwable throwable) {
                log.error("sent message=[{}] failed!", msg, throwable);
                ok[0] = false;
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("sent message=[{}] with offset=[{}] success!", msg, result.getRecordMetadata().offset());
            }
        });
        try {
            // 因为是异步发送，所以我们等待，最多10s
            future.get(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("waiting for kafka send finish failed!", e);
            return false;
        }
        return ok[0];
    }
}
