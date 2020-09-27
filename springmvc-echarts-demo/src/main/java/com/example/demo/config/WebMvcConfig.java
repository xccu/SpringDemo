package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {

    /**
     * 配置viewController，为视图提供便捷的路径映射(不用创建Controller)
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //折线图
        registry.addViewController("/line/demo1").setViewName("/line/line-demo-1");
        registry.addViewController("/line/demo2").setViewName("/line/line-demo-2");

        //饼图
        registry.addViewController("/pie/demo1").setViewName("/pie/pie-demo-1");

        //仪表盘
        registry.addViewController("/gauge/demo1").setViewName("/gauge/gauge-demo-1");
        registry.addViewController("/gauge/demo2").setViewName("/gauge/gauge-demo-2");

        //日历
        registry.addViewController("/calendar/demo1").setViewName("/calendar/calendar-demo-1");
        registry.addViewController("/calendar/demo2").setViewName("/calendar/calendar-demo-2");
    }

}

