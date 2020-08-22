package com.example.applicationcontextdemo.annotation;


import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Component
//@Inherited
public @interface IoService {
    String Id() default "";
}
