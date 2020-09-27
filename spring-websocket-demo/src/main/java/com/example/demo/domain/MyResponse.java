package com.example.demo.domain;

/**
 * 服务端向浏览器发送的消息类
 */
public class MyResponse {

    private String responseMessage;

    public MyResponse(String responseMessage){
        this.responseMessage = responseMessage;
    }

    public String getResponseMessage(){
        return responseMessage;
    }
}