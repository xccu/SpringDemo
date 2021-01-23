package com.example.demo.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity                     //1 Entity注解指明这是一个和数据库表映射的实体类
@Table(name = "user")       //映射的表名T_MENU
@Builder                    //定义builder
@NoArgsConstructor          //空构造函数
@AllArgsConstructor         //包含所有参数的构造函数
@NamedQuery(name = "User.withNameAndAgeNamedQuery", query = "select u from User u where u.name=?1 and age=?2")
public class User {
    @Id                                                     //2 指明该属性映射为数据库表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //3 注解默认使用主键生成方式为自增
    private Integer id;
    @Column
    private String name;
    @Column
    private String password;
    @Column
    @NonNull
    private Integer age;
    @Column
    private String sex;
}