package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Main {
	public static void main(String[] args) throws Exception {

		//使用AnnotationConfigApplicationContext实现基于Java的配置类加载Spring的应用上下文
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(TaskSchedulerConfig.class);


		while(true) {
			Thread.sleep(3000);
			log.info("Hello");
		}
		 
	}

}
