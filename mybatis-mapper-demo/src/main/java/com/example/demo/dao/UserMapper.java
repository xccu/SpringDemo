package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserMapper {

    @Options(useGeneratedKeys = true)       //使用生成的key
    int save(User user);

    User findByName(@Param("name") String name);

    List<User> findAll();

    int deleteByname(@Param("name") String name);

    int updateAge(User user);
}
