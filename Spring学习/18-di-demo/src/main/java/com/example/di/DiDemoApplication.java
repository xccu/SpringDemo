package com.example.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//@SpringBootApplication
public class DiDemoApplication {

	public static void main(String[] args) {

		//SpringApplication.run(DiDemoApplication.class, args);
		//AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.di");

		ApplicationContext context=new ClassPathXmlApplicationContext("BeanConfig.xml");

		//属性注入
		/*Car car = context.getBean(Car.class);
		System.out.println(car.getBrand());*/

		//按照类型匹配入参,按照索引匹配入参,联合使用类型和索引匹配入参,静态工厂
		Car car = context.getBean(Car.class);
		System.out.println(car.show());

		//通过自身类型反射匹配入参
		/*Boss boss = context.getBean(Boss.class);
		System.out.println(boss.show());*/

		//非静态工厂
		/*CarFactory carFactory = context.getBean(CarFactory.class);
		Car car = carFactory.createCar();
		System.out.println(car.show());*/
	}

}
