package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MagicBossImpl implements MagicBoss, ApplicationContextAware {
    private ApplicationContext ctx;

    public Car getCar(){
        return (Car)ctx.getBean("car");
    }

    public void setApplicationContext(ApplicationContext ctx){
        this.ctx=ctx;
    }
}
