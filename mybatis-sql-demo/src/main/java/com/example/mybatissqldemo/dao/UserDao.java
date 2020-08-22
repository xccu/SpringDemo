package com.example.mybatissqldemo.dao;

import com.example.mybatissqldemo.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {

    //insert注解，用于执行insert操作
    @Insert("insert into " +
            "Users (Name, Password, Age, Sex) " +
            "values (#{name}, #{password}, #{age}, #{sex})")
    @Options(useGeneratedKeys = true)       //使用生成的key
    int save(User user);                    //返回DML影响的数据条数

    //select注解，用于执行select操作
    @Select("select * from Users where Name = #{name}")
    User findByName(@Param("name") String name); //传入参数为name


    @Select("select * from Users")
    List<User> findAll();

    @Delete("delete from Users where name = #{name}")
    int deleteByname(@Param("name") String name);

    @Update("update Users set age = #{age} where name = #{name}")
    int updateAge(User user);
}
