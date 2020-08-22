package com.example.di;

//非静态工厂类
/*public class CarFactory{
    public Car createCar(){
        Car car=new Car();
        car.setBrand("红旗CA72");
        car.setCrop("中国一汽");
        car.setPrice(2666);
        return car;
    }
}*/


//静态工厂类
public class CarFactory{
    public static Car createCar(){
        Car car=new Car();
        car.setBrand("红旗CA72");
        car.setCrop("中国一汽");
        car.setPrice(2666);
        return car;
    }
}
