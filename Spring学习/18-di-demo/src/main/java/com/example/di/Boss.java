package com.example.di;


public class Boss {
    private String name;
    private Car car;

    public Boss(String name, Car car) {
        this.name = name;
        this.car = car;
    }

    public String show(){
        return name+":"+car.show();
    }
}