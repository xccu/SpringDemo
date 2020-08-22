package com.example.annotation;

import java.lang.annotation.*;

/**
 * 方法访问角色注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface VisitorRole {
    String value();
}