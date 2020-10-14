package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //加载配置文件，启动IoC容器
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");

        Boss boss1=factory.getBean("boss1",Boss.class);
        Boss boss2=factory.getBean("boss2",Boss.class);
        Boss boss3=factory.getBean("boss3",Boss.class);
        Boss boss4=factory.getBean("childtBoss",Boss.class);

        System.out.println(boss1.toString());
        System.out.println(boss2.toString());
        System.out.println(boss3.toString());
        System.out.println(boss4.toString());
    }
}
