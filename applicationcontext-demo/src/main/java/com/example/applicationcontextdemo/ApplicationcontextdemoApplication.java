package com.example.applicationcontextdemo;

import com.example.applicationcontextdemo.annotation.IoService;
import com.example.applicationcontextdemo.model.User;
import com.example.applicationcontextdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class ApplicationcontextdemoApplication  implements ApplicationRunner {

    @Autowired
    ApplicationContextProvider  provider;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationcontextdemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UserService userService = provider.getBean(UserService.class);
        User user = userService.GetUser();
        log.info("User:{}",user);
        List<User> userList = userService.GetUsers();
        log.info("Users:{}",userList);


        Map map = provider.getBeansWithAnnotation(IoService.class);
        log.info("IOService:{}",map);
    }

}
