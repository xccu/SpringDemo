package com.example.annotation;

import org.springframework.stereotype.Component;

@Component("person")   //此处通过注解的方式定义受管Bean
public class Person {
    private String userName = "Charlie";

    @VisitorRole("ADMIN")   //自定义注解的使用。只有具有ADMIN角色才能调用本方法。
    public String say(){
        return "I'm " + userName;
    }
}