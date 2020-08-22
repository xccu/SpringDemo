package com.example.autowireddemo;

import org.springframework.stereotype.Component;

@Component
public class ENService implements IService {

    private String name="";

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String sayHello() {
        return "Hello "+name;
    }
}
