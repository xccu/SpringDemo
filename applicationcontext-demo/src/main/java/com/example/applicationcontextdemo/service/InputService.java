package com.example.applicationcontextdemo.service;

import com.example.applicationcontextdemo.annotation.IoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@IoService(Id = "1")
public class InputService {

    public void input(String message){
        log.info("input:{}",message);
    }
}
