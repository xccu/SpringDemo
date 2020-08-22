package com.example.redis;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Data
public class User implements Serializable {  //必须继承Serializable才能被redis template序列化
    private String name;
    private int age;
    private String species;
}
