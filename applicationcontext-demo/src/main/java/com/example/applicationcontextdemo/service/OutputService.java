package com.example.applicationcontextdemo.service;

import com.example.applicationcontextdemo.annotation.IoService;
import org.springframework.stereotype.Service;

@Service
@IoService(Id = "2")
public class OutputService {

    public String message(){
        return "Hello World";
    }
}
