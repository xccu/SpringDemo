package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig  extends WebMvcConfigurerAdapter {

    /**
     * 配置viewController，为ws.html提供便捷的路径映射(不用创建Controller)
	 * @param registry
	 */
	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {

	 	//http://localhost:8090/
	 	registry.addViewController("").setViewName("/index");
	 }

}
