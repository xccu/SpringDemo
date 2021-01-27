package com.example.demo;

import com.example.demo.dao.UserRepository;
import com.example.demo.support.CustomRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class DemoApplication {

    @Autowired
    UserRepository userepository;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
