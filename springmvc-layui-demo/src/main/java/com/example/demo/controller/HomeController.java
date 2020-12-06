package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/layui")
public class HomeController {

    //http://localhost:8080/layui/index
    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    //http://localhost:8080/layui/table
    @GetMapping(path = "/table")
    public String table() {
        return "table";
    }

    //http://localhost:8080/layui/button
    @GetMapping(path = "/button")
    public String button() {
        return "button";
    }

    //http://localhost:8080/layui/users
    @GetMapping(path = "/users")
    public String users() {
        return "users-table";
    }

}
