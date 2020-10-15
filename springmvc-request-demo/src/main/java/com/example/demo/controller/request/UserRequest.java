package com.example.demo.controller.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserRequest {
    //非空 不满足条件会导致校验失败
    @NotEmpty
    private String name;
    //非null 不满足条件会导致校验失败
    @NotNull
    private int id;
}
