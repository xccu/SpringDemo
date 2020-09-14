package com.example.importdemo;

import com.example.maven.Output;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImportDemoApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(ImportDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Output output = new Output();
		output.print("Hello World");
	}

}
