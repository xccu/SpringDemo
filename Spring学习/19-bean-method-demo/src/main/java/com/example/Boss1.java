package com.example;

public class Boss1 implements MagicBoss{
    public Car getCar(){
        Car car=new Car();
        car.setBrand("宝马");
        return car;
    }
}
