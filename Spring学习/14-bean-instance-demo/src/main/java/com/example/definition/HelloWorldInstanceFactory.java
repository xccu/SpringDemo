package com.example.definition;

public class HelloWorldInstanceFactory {
    /**
     * 工厂方法
     * @param message
     * @return
     */
    public HelloWorld newInstance(String message){
        //需要返回的bean实例
        return new HelloWorldImpl(message);
    }
}
