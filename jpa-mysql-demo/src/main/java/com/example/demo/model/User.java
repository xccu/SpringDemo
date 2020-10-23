package com.example.demo.model;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity                     //数据实体
@Table(name = "Users")     //映射的表名T_MENU
@Builder                    //定义builder
@Data                       //定义getter、setter、toString
@ToString(callSuper = true) //打印出父类的属性
@NoArgsConstructor          //空构造函数
@AllArgsConstructor         //包含所有参数的构造函数
public class User extends BaseEntity implements Serializable {
    /*@Id
    @Column(name = "UserId")
    private Integer userId;*/
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private Integer age;
    @Column(name = "Sex")
    private String sex;
}
