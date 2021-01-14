package com.example.demo;

import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
@MapperScan("com.example.demo.mapper")
public class DemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Autowired
    private UserMapper userMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("----- selectAll method test ------");
        List<User> userList = userMapper.selectList(null);
        //Assert.assertEquals(5, userList.size());
        //userList.forEach(System.out::println);

        log.info("Find Users: {}", userList);
    }
}
