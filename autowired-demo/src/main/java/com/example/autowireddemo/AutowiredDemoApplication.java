package com.example.autowireddemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AutowiredDemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AutowiredDemoApplication.class, args);
    }

    //注入实现接口
    @Autowired
    //@Qualifier(value = "ZHService")
    private IService ZHService;

    @Autowired
    //@Qualifier(value = "ENService")
    private IService ENService;

    //注入实例化类
    @Autowired
    private BLL bll;

    /*@Autowired
    @Qualifier(value = "MyPlugin")
    private IPlugin myPlugin;

    @Autowired
    @Qualifier(value = "YourPlugin")
    private IPlugin yourPlugin;*/


    @Override
    public void run(String... args) throws Exception {
        ZHService.setName("Weslie");
        System.out.println(ZHService.sayHello());

        ENService.setName("Wolffy");
        System.out.println(ENService.sayHello());

        bll.show();
    }

}
