package com.example.springtransactionaldemo.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Account implements Serializable {
    private Integer id;
    private String name;

    public Account(Integer id,String name){
        this.id=id;
        this.name=name;
    }
}
