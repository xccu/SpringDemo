package com.example;

import com.example.definition.HelloWorld;
import com.example.definition.HelloWorldImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        sayHelloWorldInstanceFactory();
    }

    /**
     * 使用无参数的构造函数来实例化bean
     */
    public static void sayHelloWordWithNoArgs(){
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");
        HelloWorld helloWorld=factory.getBean("helloWorldWithNoArgs",HelloWorldImpl.class);
        helloWorld.sayHello();
    }

    /**
     * 使用有参数的构造函数来实例化bean
     */
    public static void sayHelloWorldWithArgs(){
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");
        HelloWorld helloWorld=factory.getBean("helloWorldWithArgs",HelloWorldImpl.class);
        helloWorld.sayHello();
    }

    public static void sayHelloWordStaticFactory(){
        //读取配置文件，实例化一个IoC容器
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");
        //从容器中获取bean，注意此处完全“面向接口编程而不是面向实现”
        HelloWorld helloWorld=factory.getBean("helloWorldStaticFactory",HelloWorld.class);
        //执行业务逻辑
        helloWorld.sayHello();
    }

    public static void sayHelloWorldInstanceFactory(){
        //读取配置文件，实例化一个IoC容器
        BeanFactory factory= new FileSystemXmlApplicationContext("src/main/config.xml");
        //从容器中获取bean，注意此处完全“面向接口编程而不是面向实现”
        HelloWorld helloWorld=factory.getBean("helloWorldInstance",HelloWorld.class);
        //执行业务逻辑
        helloWorld.sayHello();
    }

}
