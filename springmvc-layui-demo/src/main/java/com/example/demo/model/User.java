package com.example.demo.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
    private String email;
    private int power;
    private String avatarUrl;
}
