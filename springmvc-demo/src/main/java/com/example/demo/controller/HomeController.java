package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/home")
public class HomeController {

    /**
     * 返回字符串
     * http://localhost:8080/home/index
     * @return
     */
    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    /**
     * 返回视图
     * http://localhost:8080/home
     * @return
     */
    @GetMapping()
    public ModelAndView test() {
        return new ModelAndView("index");
    }
}
