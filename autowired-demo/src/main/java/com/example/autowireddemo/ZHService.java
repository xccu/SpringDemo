package com.example.autowireddemo;

import org.springframework.stereotype.Component;

@Component
public class ZHService implements IService {
    private String name="";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String sayHello() {
        return "你好，"+ name;
    }
}
