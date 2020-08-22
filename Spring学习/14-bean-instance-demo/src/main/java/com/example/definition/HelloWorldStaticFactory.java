package com.example.definition;

public class HelloWorldStaticFactory {
    /**
     * 工厂方法
     * @param message
     * @return
     */
    public static HelloWorld newInstance(String message){
        //返回带参数的HelloWorldImpl构造的helloWorld实例
        return new HelloWorldImpl(message);
    }
}
