package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 仪表盘
 */
@Controller
@RequestMapping("/gauge")
public class GaugeController {

    @GetMapping(path = "/demo1")
    public ModelAndView demo1() {
        return new ModelAndView("gauge/gauge-demo-1");
    }

    @GetMapping(path = "/demo2")
    public ModelAndView demo2() {
        return new ModelAndView("gauge/gauge-demo-2");
    }
}
