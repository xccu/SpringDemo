package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class DemoApplication  implements ApplicationRunner {

    @Autowired
    private UserService service;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            //通过name查找
            User user = service.getUserByName("Weslie");
            log.info("Find User: {}", user);

            //查找全部
            List<User> userList = service.getUAllUsers();
            log.info("Find Users: {}", userList);

            //新增一条记录
            user.setName("test");
            int i = service.insertUser(user);

            //删除一条记录
            log.info("insert Users: {}", i);
            i = service.deleteUser("test");
            log.info("delete Users: {}", i);

            //所有user年龄加一
            i = service.ageAddOne();
            log.info("update Users: {}", i);

            //所有user年龄减一
            //i = service.ageSubOne();
            //log.info("update Users: {}", i);
        }
        catch(Exception ex) {
            log.error(ex.toString());
        }
    }
}
