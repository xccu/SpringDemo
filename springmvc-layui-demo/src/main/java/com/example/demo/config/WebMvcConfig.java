package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     * 配置viewController，为视图提供便捷的路径映射(不用创建Controller)
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        //柱状图
        //http://localhost:8090/bar/demo1
        //registry.addViewController("/bar/demo1").setViewName("/bar/bar-demo-1");

        //http://localhost:8080/layui/table
        registry.addViewController("/layui/table").setViewName("/table");
        //http://localhost:8080/layui/button
        registry.addViewController("/layui/button").setViewName("/button");
        //http://localhost:8080/layui/layer
        registry.addViewController("/layui/layer").setViewName("/layer");
        //http://localhost:8080/layui/form
        registry.addViewController("/layui/form").setViewName("/form");

        //http://localhost:8080/layui/users
        registry.addViewController("/layui/users").setViewName("/users-table");
        //http://localhost:8080/layui/user
        registry.addViewController("/layui/user").setViewName("/user");
    }
}

