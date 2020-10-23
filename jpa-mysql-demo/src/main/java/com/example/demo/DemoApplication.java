package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.*;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class DemoApplication implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initOrders();
    }

    private void initOrders() {

        try {
            //通过builder初始赋值
            User user = User.builder()
                    .name("User1")
                    .password("123")
                    .age(20)
                    .sex("male")
                    .build();
            //保存数据
            //userRepository.save(user);
            //log.info("user: {}", user);

            //userRepository.deleteById(Integer.valueOf(10));

            User geted = userRepository.findById(Integer.valueOf(1)).get();
            log.info("user: {}", geted);

            Iterable<User> iter = userRepository.findAll();
            log.info("users: {}", iter);


        }
        catch (Exception ex){
            log.error(ex.toString());
        }
    }
}
