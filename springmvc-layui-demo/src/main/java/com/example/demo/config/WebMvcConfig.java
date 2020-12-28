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

        //http://localhost:8080/layui/table
        registry.addViewController("/layui").setViewName("/admin");
        //http://localhost:8080/layui/table
        registry.addViewController("/layui/table").setViewName("/table");
        //http://localhost:8080/layui/button
        registry.addViewController("/layui/button").setViewName("/button");
        //http://localhost:8080/layui/layer
        registry.addViewController("/layui/layer").setViewName("/layer");
        //http://localhost:8080/layui/form
        registry.addViewController("/layui/form").setViewName("/form");
        //http://localhost:8080/layui/nav
        registry.addViewController("/layui/nav").setViewName("/nav");
        //http://localhost:8080/layui/icon
        registry.addViewController("/layui/icon").setViewName("/icon");
        //http://localhost:8080/layui/laydate
        registry.addViewController("/layui/laydate").setViewName("/laydate");
        //http://localhost:8080/layui/badge
        registry.addViewController("/layui/badge").setViewName("/badge");
        //http://localhost:8080/layui/progress
        registry.addViewController("/layui/progress").setViewName("/progress");

        //http://localhost:8080/layui/users
        registry.addViewController("/layui/user").setViewName("/users-table");
        //http://localhost:8080/layui/user-ajax？1
        registry.addViewController("/layui/user-ajax").setViewName("/user-ajax");
        //http://localhost:8080/layui/idCard
        registry.addViewController("/layui/idCard").setViewName("/idCard");
        //http://localhost:8080/layui/idCard
        registry.addViewController("/layui/idCard-shell").setViewName("/idCard-shell");
    }
}

