package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //加载配置文件，启动IoC容器
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");

        Boss boss=factory.getBean("boss", Boss.class);

        System.out.println(boss.toString());

    }
}
