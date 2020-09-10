package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloController {

    /**
     * 返回视图
     * http://localhost:8080/hello/index
     * @return
     */
    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    /**
     * 返回视图
     * http://localhost:8080/hello
     * @return
     */
    @GetMapping()
    public ModelAndView test() {
        return new ModelAndView("index");
    }
}