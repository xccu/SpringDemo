package com.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        //加载配置文件，启动IoC容器
        //BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");
        ApplicationContext contect = new FileSystemXmlApplicationContext("src/main/config.xml");

        Boss1 boss1=contect.getBean("boss1",Boss1.class);

        MagicBoss magicBoss=contect.getBean("magicBoss",MagicBoss.class);

        MagicBossImpl magicBossImpl =new MagicBossImpl();
        magicBossImpl.setApplicationContext(contect);

        System.out.println(boss1.getCar().getBrand());          //美人豹
        System.out.println(magicBoss.getCar().getBrand());      //红旗CA72
        System.out.println(magicBossImpl.getCar().getBrand());  //红旗CA72
    }
}
