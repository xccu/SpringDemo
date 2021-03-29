package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService service;

    /**
     * 查询全部
     * localhost:8080
     * @return
     */
    @RequestMapping("/")
    public List<User> all(){
        List<User> users = service.findAll();
        return users;
    }

    /**
     * localhost:8080/rollback?name=Weslie&age=12
     * @param user
     * @return
     */
    @RequestMapping("/rollback")
    public User rollback(User user){
        try {
            return service.saveUserWithRollback(user);
        }
        catch (Exception ex){
            return new User();
        }
    }

    /**
     * http://localhost:8080/rollback?name=Weslie&age=12
     * @param user
     * @return
     */
    @RequestMapping("/norollback")
    public User noRollback(User user){
        try {
            return service.saveUserWithoutRollback(user);
        }
        catch (Exception ex){
            return new User();
        }
    }

}
