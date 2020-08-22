package com.example;

import com.example.definition.HelloWorld;
import com.example.definition.HelloWorldImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        //使用FileSystemXmlApplicationContext加载配置文件信息
        BeanFactory beanFactory= new FileSystemXmlApplicationContext("src/main/config.xml");

        //获取bean实例
        //HelloWorld helloWorld=beanFactory.getBean(HelloWorldImpl.class);

        //2.通过id获取bean实例
        //HelloWorld helloWorld=beanFactory.getBean("HelloWorldById",HelloWorldImpl.class);

        //3.通过name获取bean实例
        //HelloWorld helloWorld=beanFactory.getBean("HelloWorldByName",HelloWorldImpl.class);

        //helloWorld.sayHello();

        //4.通过id和name分别获取bean实例
        /*HelloWorld helloWorld01=beanFactory.getBean("HelloWorldById01",HelloWorldImpl.class);
        HelloWorld helloWorld02=beanFactory.getBean("HelloWorldByName01",HelloWorldImpl.class);
        helloWorld01.sayHello();
        helloWorld02.sayHello();*/



        //5.指定多个name分别获取bean实例
        /*HelloWorld helloWorld1=beanFactory.getBean("bean1",HelloWorldImpl.class);
        HelloWorld helloWorld01=beanFactory.getBean("alias01",HelloWorldImpl.class);
        HelloWorld helloWorld02=beanFactory.getBean("alias02",HelloWorldImpl.class);
        HelloWorld helloWorld03=beanFactory.getBean("alias03",HelloWorldImpl.class);
        helloWorld1.sayHello();
        helloWorld01.sayHello();
        helloWorld02.sayHello();
        helloWorld03.sayHello();

        HelloWorld helloWorld2=beanFactory.getBean("bean2",HelloWorldImpl.class);
        HelloWorld helloWorld11=beanFactory.getBean("alias11",HelloWorldImpl.class);
        HelloWorld helloWorld12=beanFactory.getBean("alias12",HelloWorldImpl.class);
        HelloWorld helloWorld13=beanFactory.getBean("alias13",HelloWorldImpl.class);
        helloWorld2.sayHello();
        helloWorld11.sayHello();
        helloWorld12.sayHello();
        helloWorld13.sayHello();*/

        //6.使用别名和alias标签来获取bean实例
        HelloWorld helloWorld01=beanFactory.getBean("bean3",HelloWorldImpl.class);
        HelloWorld helloWorld02=beanFactory.getBean("alias21",HelloWorldImpl.class);
        HelloWorld helloWorld03=beanFactory.getBean("alias22",HelloWorldImpl.class);
        helloWorld01.sayHello();
        helloWorld02.sayHello();
        helloWorld03.sayHello();

    }
}
