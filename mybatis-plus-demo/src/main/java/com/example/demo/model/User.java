package com.example.demo.model;

import javax.persistence.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
//@TableName("sys_user")
public class User {
    @TableId
    private Integer id;
    private String name;
    private String password;
    private Integer age;
    @TableField("gender")
    private String sex;
    private String race;
}