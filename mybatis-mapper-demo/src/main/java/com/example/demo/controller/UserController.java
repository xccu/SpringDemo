package com.example.demo.controller;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserMapper mapper;

    //http://localhost:8080/getUser?name=Weslie
    @RequestMapping("/getUser")
    public User getUser(String name){
        User user =mapper.findByName(name);
        log.info("Find User: {}", user);
        return user;
    }

    //http://localhost:8080/addAge?name=wolffy
    @RequestMapping("/addAge")
    public String addAge(String name){
        User user = mapper.findByName(name);
        user.setAge(user.getAge()+1);
        int i= mapper.updateAge(user);
        log.info("UPdate User count: {}", i);
        return "success："+i;
    }

    //http://localhost:8080/subAge?name=wolffy
    @RequestMapping("/subAge")
    public String subAge(String name){
        User user = mapper.findByName(name);
        user.setAge(user.getAge() - 1);
        int i = mapper.updateAge(user);
        log.info("Update User count: {}", i);
        return "success：" + i;
    }

    //http://localhost:8080/add
    @RequestMapping("/add")
    public String addUser(){
        User user = new User();
        user.setName("test");
        user.setAge(10);
        user.setPassword("abc");
        user.setSex("male");

        int i = mapper.save(user);
        log.info("save Users: {}", i);
        return "success:"+i;
    }

    //http://localhost:8080/delete
    @RequestMapping("/delete")
    public String deleteUser(){
        int i = mapper.deleteByname("test");
        log.info("delete Users: {}", i);
        return "success:"+i;
    }

    //http://localhost:8080/getAll
    @RequestMapping("/getAll")
    public List getUserList(String username, String password){
        return mapper.findAll();
    }
}
