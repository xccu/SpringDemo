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

    /**
     * http://localhost:8090/gauge/demo1
     * @return
     */
    @GetMapping(path = "/demo1")
    public String demo1() {
        return "gauge/gauge-demo-1";
    }

    /**
     * http://localhost:8090/gauge/demo2
     * @return
     */
    @GetMapping(path = "/demo2")
    public String demo2() {
        return "gauge/gauge-demo-2";
    }
}
