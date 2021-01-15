package com.example.demo.controller;

import com.example.demo.service.ComplexProducer;
import com.example.demo.service.Producer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Resource
    private Producer producer;

    @GetMapping("/publish")
    public String sendMsg(@RequestParam("msg") String msg) {
        this.producer.sendMessage(msg);
        return "send msg[" + msg + "] success!";
    }

    @Resource
    private ComplexProducer complexProducer;

    @GetMapping("/publish2")
    public String conplexSendMsg(@RequestParam("msg") String msg) {
        this.complexProducer.sendMessageSync("msg",msg);
        return "send msg[" + msg + "] success!";
    }
}