package com.example.apiclientdemo;

import com.alibaba.fastjson.JSONArray;
import com.example.apiclientdemo.model.User;
import com.example.apiclientdemo.service.APIService;
import com.example.apiclientdemo.service.FeignService;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@SpringBootApplication
public class ApiClientDemoApplication implements ApplicationRunner  {

	@Autowired
	APIService apiService;

	public static void main(String[] args) {
		SpringApplication.run(ApiClientDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		restTemplateTest();
		//feginTest();
	}

	private void restTemplateTest(){
		Map<String, Object> params = new HashMap<>();
		params.put("id",1);
		String data = apiService.get("http://localhost:8080/user/getbyid/{id}",params);

		String jsonString = apiService.get("http://localhost:8080/user/getall");
		List<User> userInfos = (List<User>) JSONArray.parseArray(jsonString, User.class);

		User user = new User();
		user.setId(6);
		user.setName("Weslie");
		user.setAge(12);
		user.setSex("male");

		apiService.post("http://localhost:8080/user/adduser",user);

		apiService.put("http://localhost:8080/user/updateone",user);
	}

	private FeignService init()
	{
		return Feign.builder()
				.decoder(new JacksonDecoder())
				.encoder(new JacksonEncoder())
				.target(FeignService.class, "http://localhost:8080");
	}

	private void feginTest(){
		try {
			FeignService feignService = init();
			User userInfos = feignService.getUserByID(1);
			log.info(userInfos.toString());

			User user = new User();
			user.setId(6);
			user.setName("Weslie");
			user.setAge(12);
			user.setSex("male");
			feignService.postUser(user);

		}
		catch (Exception ex){
			log.error(ex.toString());
		}
	}

}
