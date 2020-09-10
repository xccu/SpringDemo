package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 折线图
 */
@Controller
@RequestMapping("/line")
@Slf4j
public class LineController {

    @GetMapping(path = "/demo1")
    public ModelAndView demo1() {
        return new ModelAndView("line/line-demo-1");
    }

    @GetMapping(path = "/demo2")
    public ModelAndView demo2() {
        return new ModelAndView("line/line-demo-2");
    }
}
