package com.example.autowireddemo;

public class MyPlugin implements IPlugin {

    int num = 0;
    public MyPlugin(int num) {
        this.num=num;
    }

    @Override
    public void show() {
        System.out.println("MyPlugin : "+num);
    }
}
