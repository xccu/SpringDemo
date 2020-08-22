package com.example.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(DemoConfig.class);

        Person person =  context.getBean(Person.class);

        System.out.println(person.say());

        context.close();
    }
}
