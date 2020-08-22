package com.example.jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Slf4j
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //屏蔽springboot自带数据源
public class Main implements ApplicationRunner {

    //jedisPool注入
    @Autowired
    private JedisPool jedisPool;
    //JedisPoolConfig注入
    @Autowired
    private JedisPoolConfig jedisPoolConfig;

    public static void main(String[] args) { SpringApplication.run(Main.class, args); }

    //配置Bean jedisPool
    @Bean(destroyMethod = "close")
    public JedisPool jedisPool(@Value("${redis.host}") String host) {
        return new JedisPool(jedisPoolConfig(), host);
    }

    //配置Bean JedisPoolConfig
    @Bean
    @ConfigurationProperties("redis")
    public JedisPoolConfig jedisPoolConfig() {
        return new JedisPoolConfig();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<User> users = new ArrayList<User>();
        users.add(User.builder().name("Weslie").age(12).species("goat").build());
        users.add(User.builder().name("Wolffy").age(34).species("wolf").build());

        //输出jedisPoolConfig配置信息
        log.info(jedisPoolConfig.toString());

        //获取jedis实例
        //try-with-resource语法，try结束后会把这个resource关闭,类似c#的using()语法
        try (Jedis jedis = jedisPool.getResource()) {

            //执行HSET命令以Hash类型写入Redis
            users.forEach(c -> {
                jedis.hset("user-list",
                        c.getName(),
                        Integer.toString(c.getAge()));
            });

            //对应Redis命令：
            //HGETALL springbucks-menu
            Map<String, String> menu = jedis.hgetAll("user-list");
            log.info("user: {}", menu);

            //对应Redis命令：
            //HGET user-list Weslie
            String age = jedis.hget("user-list", "Weslie");
            log.info("Weslie - {}", Integer.parseInt(age));
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}