package com.example.definition;

public class HelloWorldImpl implements HelloWorld{
    private String message;

    /**
     * 空构造器
     */
    HelloWorldImpl(){
        message="Hello World";
    }

    /**
     * 带参数的构造器
     * @param message
     */
    HelloWorldImpl(String message){
        this.message=message;
    }
    public void sayHello() {
        System.out.println(message);
    }
}
