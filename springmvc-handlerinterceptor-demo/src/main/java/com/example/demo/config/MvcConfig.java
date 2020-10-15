package com.example.demo.config;

import com.example.demo.inteceptor.CustomInteceptor;
//import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

/**
 * WebMvc配置类
 * 承基ebMvcConfigurer接口，配置web Mvc
 * @Configuration注解表示配置类，工程启动后生效
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加Inteceptor
        registry.addInterceptor(new CustomInteceptor())
                .addPathPatterns("/user/**")    //指定拦截/user/下的url请求
                .addPathPatterns("/order/**");  //指定拦截/order/下的url请求
    }

    /**
     * Jackson ObjectMapper自定义
     * @return
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        return builder -> {
            //定义返回的ResponseBody json缩进
            builder.indentOutput(true);
            //定义返回的ResponseBody 日期时区
            builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }
}
