package com.example;

import java.util.Set;

public class Boss {

    private Car carId = new Car();

    @Override
    public String toString() {
        return "Boss{car=" + carId + '}';
    }

    public void setCarId(Car carId) {
        this.carId = carId;
    }

    public Car getCarId() {
        return carId;
    }
}