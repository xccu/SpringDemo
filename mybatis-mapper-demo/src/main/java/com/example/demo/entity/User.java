package com.example.demo.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@Alias( "user")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Integer userId;
    private String name;
    private String password;
    private Integer age;
    private String sex;
}
