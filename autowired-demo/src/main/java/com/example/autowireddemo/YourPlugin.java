package com.example.autowireddemo;

public class YourPlugin implements IPlugin {

    int num = 0;
    public YourPlugin(int num) {
        this.num=num;
    }

    @Override
    public void show() {
        System.out.println("YourPlugin : "+num);
    }
}