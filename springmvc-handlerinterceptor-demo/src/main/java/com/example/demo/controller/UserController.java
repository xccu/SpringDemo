package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取所有list，以json形式返回
     * url:"http://localhost:8080/user/"
     * @return
     */
    @RequestMapping(value = "/")
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    /**
     * url:"http://localhost:8080/user/getbyid/1"
     *
     * @param id
     * @return
     */

    // == @RequestMapping(value = "/user/getbyid/{id}", method = RequestMethod.GET)
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }
}