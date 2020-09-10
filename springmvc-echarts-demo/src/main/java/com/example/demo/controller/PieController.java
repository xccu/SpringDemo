package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 饼图
 */
@Controller
@RequestMapping("/pie")
@Slf4j
public class PieController {

    @GetMapping(path = "/demo1")
    public ModelAndView demo1() {
        return new ModelAndView("pie/pie-demo-1");
    }
}
