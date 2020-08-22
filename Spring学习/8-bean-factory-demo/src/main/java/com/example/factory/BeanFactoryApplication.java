package com.example.factory;

public class BeanFactoryApplication {

    /**
     * 测试方法.
     *
     * @param args
     */
    public static void main(String[] args) {
        BeanFactory factory = new BeanFactory();
        factory.init("config.xml");
        User user = (User) factory.getBean("user1");
        System.out.println("userName=" + user.getUserName());
        System.out.println("password=" + user.getPassword());

        user = (User) factory.getBean("user2");
        System.out.println("userName=" + user.getUserName());
        System.out.println("password=" + user.getPassword());
    }
}
