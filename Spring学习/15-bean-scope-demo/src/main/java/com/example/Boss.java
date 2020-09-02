package com.example;

public class Boss {
    private String name;
    private Car car;

    public Boss() {
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" + "name='" + name + '\'' + ", car=" + car + '}';
    }
}
