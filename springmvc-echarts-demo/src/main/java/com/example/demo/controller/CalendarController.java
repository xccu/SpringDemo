package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 柱状图
 */
@Controller
@RequestMapping("/calendar")
public class CalendarController {

    @GetMapping(path = "/demo1")
    public String demo1() {
        return "calendar/calendar-demo-1";
    }

    @GetMapping(path = "/demo2")
    public String demo2() {
        return "calendar/calendar-demo-2";
    }
}
