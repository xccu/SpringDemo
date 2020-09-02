package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        //加载配置文件，启动IoC容器
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/conf-scope.xml");
        //获得bean实例
        Boss boss1=factory.getBean("boss1",Boss.class);
        Boss boss2=factory.getBean("boss2",Boss.class);
        Boss boss3=factory.getBean("boss3",Boss.class);
        System.out.println(boss1.getCar());
        System.out.println(boss2.getCar());
        System.out.println(boss3.getCar());
    }
}