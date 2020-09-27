package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;


/**
 * 1.@EnableWebSocketMessageBroker 注解开启使用STOMP协议来传输基于代理（message broker）的消息
 * 这时控制器支持使用@MessageMapping，就像使用@RequestMapping一样
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    /**
     * 2.注册STOMP协议的节点（endpoint），并映射到指定的url
     * @param registry
     */
	@Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //3.注册一个STOMP的endpoint，并指定使用SockJS协议
	    registry.addEndpoint("/endpointDemo").withSockJS();
    }


    /**
     * 4.配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //5.广播式应配置一个/topic消息
        registry.enableSimpleBroker("/topic");
    }

}
