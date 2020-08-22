package com.example.di;

//属性注入
/*public class Car {
    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBrand() {
        return this.brand;
    }
}*/

//构造函数注入:按照类型匹配入参方法
/*public class Car {
    private String brand;
    private double price;

    public Car(String brand, double price) {
        this.brand = brand;
        this.price = price;
    }

    public String show(){
        return brand+","+price;
    }
}*/

//构造函数注入:按照索引匹配入参，通过自身类型反射匹配入参
/*public class Car{
    private String brand;
    private String corp;
    private double price;

    public Car(String brand, String corp, double price) {
        this.brand = brand;
        this.corp = corp;
        this.price = price;
    }

    public String show(){
        return brand+","+corp+","+price;
    }
}*/


//构造函数注入:联合使用类型和索引匹配入参方式
/*public class Car{
    private String brand;
    private String corp;
    private double price;
    private int maxSpeed;

    public Car(String brand, String corp, double price) {
        this.brand = brand;
        this.corp = corp;
        this.price = price;
    }

    public Car(String brand, String corp, int maxSpeed) {
        this.brand = brand;
        this.corp = corp;
        this.maxSpeed = maxSpeed;
    }

    public String show(){
        return brand+","+corp+","+price+","+maxSpeed;
    }
}*/

//工厂注入
public class Car{
    private String brand;
    private String corp;
    private double price;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCrop() {
        return corp;
    }

    public void setCrop(String corp) {
        this.corp = corp;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String show(){
        return brand+","+corp+","+price;
    }
}
