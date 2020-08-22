package com.example.autowireddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class BLL {
    void show() {
        System.out.println("this is a BLL Class");
    }
}
