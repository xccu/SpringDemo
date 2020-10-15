package com.example;

public class Person {
    /*//因为Person类要引用IHello接口，所以将IHello作为类的一个属性
    private IHello iHelloMessage;

    *//**
     * 自动生成get方法
     * @return
     *//*
    public IHello getiHelloMessage() {
        return iHelloMessage;
    }

    *//**
     * 自动生成set方法
     * @param iHello
     *//*
    public void setiHelloMessage(IHello iHelloMessage) {
        this.iHelloMessage = iHelloMessage;
    }


    *//**
     * person类使用sayHello方法来调用借口的sayHello方法
     * @return
     *//*
    public String sayHello(){
        return this.iHelloMessage.sayHello();
    }*/

    public String sayHello(){
        return "hello";
    }
}