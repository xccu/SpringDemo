package com.example;

import java.util.Set;

public class Boss {

    private String carId;
    private Car car = new Car();

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarId() {
        return carId;
    }

    @Override
    public String toString() {
        return "Boss{car=" + car + '}';
    }
}