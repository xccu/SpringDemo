package com.example.demo.controller;

import com.example.demo.domain.MyMessage;
import com.example.demo.domain.MyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {
    /**
     * 1.当浏览器向服务端发送请求时，通过@MessageMapping映射/welcome这个地址，类似于@RequestMapping
     * 2.当服务端有消息时，会对订阅了@SendTo中的路径的浏览器发送消息
     * @param message
     * @return
     * @throws Exception
     */
	@MessageMapping("/welcome")
	@SendTo("/topic/getResponse")
	public MyResponse say(MyMessage message) throws Exception {
		Thread.sleep(3000);
		return new MyResponse("Welcome, " + message.getName() + "!");
	}

}
