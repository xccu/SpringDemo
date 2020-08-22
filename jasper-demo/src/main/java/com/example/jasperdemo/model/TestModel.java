package com.example.jasperdemo.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class TestModel {

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private int age;
}
