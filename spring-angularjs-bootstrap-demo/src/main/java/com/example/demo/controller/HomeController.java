package com.example.demo.controller;

import com.example.demo.model.Person;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 */
@RestController
public class HomeController {

    @RequestMapping(value="/search",produces={MediaType.APPLICATION_JSON_VALUE})
    public Person search(String personName){
        return new Person(personName, 32, "hefei");
    }
}
