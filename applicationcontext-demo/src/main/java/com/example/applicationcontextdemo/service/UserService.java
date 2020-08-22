package com.example.applicationcontextdemo.service;

import com.example.applicationcontextdemo.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public User GetUser(){
        User user = new User();
        user.setName("Weslie");
        user.setAge(12);
        return user;
    }

    public List<User> GetUsers(){

        List<User> users = new ArrayList<>();

        User user = new User();
        user.setName("Weslie");
        user.setAge(12);
        users.add(user);

        user = new User();
        user.setName("Wolffy");
        user.setAge(34);
        users.add(user);

        user = new User();
        user.setName("Tibbie");
        user.setAge(11);
        users.add(user);

        user = new User();
        user.setName("Sparky");
        user.setAge(12);
        users.add(user);

        user = new User();
        user.setName("Paddi");
        user.setAge(10);
        users.add(user);

        return users;
    }
}
