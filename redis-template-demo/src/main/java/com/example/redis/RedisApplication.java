package com.example.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;


@Slf4j
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class}) //屏蔽springboot自带数据源
public class RedisApplication implements ApplicationRunner {

    private static final String CACHE = "user";

    //@Autowired  //AutoWired装配失败问题参见：https://www.cnblogs.com/muxi0407/p/11800999.html
    @Resource
    private RedisTemplate<String, User> redisTemplate; //RedisTemplate 键值对缓存支持，需要连接池：commons-pool2

    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    //装配Bean ：hashOperations
    @Bean
    public HashOperations<String, String, User> hashOperations(){
        return redisTemplate.opsForHash();
    }

    //注入Bean：hashOperations
    @Autowired
    HashOperations<String, String, User> hashOperations;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        //从redis获取
        getFromCache("Weslie");
        getFromCache("Wolffy");

        List<User> users = new ArrayList<User>();
        users.add(User.builder().name("Weslie").age(12).species("goat").build());
        users.add(User.builder().name("Wolffy").age(34).species("wolf").build());


        //获取成功后写入缓存
        /*if (users.size()>0) {
            log.info("Put User {} to Redis.", "weslie");
            User user = users.get(0);
            hashOperations.put(CACHE, "weslie", user);
        }*/

        //写入缓存,一个HashValue可以写入多个对象
        users.forEach(c -> {
            log.info("Put User {} to Redis.", c.getName());
            hashOperations.put(CACHE, c.getName(), c);
        });

        //设置缓存过期时间：1分钟
        redisTemplate.expire(CACHE, 1, TimeUnit.MINUTES);

        //从redis获取
        getFromCache("Weslie");
        getFromCache("Wolffy");
    }

    private void getFromCache(String name) {
        //查看是否存在key为 springbucks-coffee 的缓存，如果缓存已经存在，则通过缓存获取
        if (redisTemplate.hasKey(CACHE) && hashOperations.hasKey(CACHE, name)) {
            log.info("User Found: {}", hashOperations.get(CACHE, name));
        }
        else{
            log.info("User Not Found {}",name);
        }
    }

}

//获取所有KEY： key*
//user的Key值：xac\xed\x00\x05t\x00\x04user
//获取缓存信息： HGETALL "\xac\xed\x00\x05t\x00\x04user"

/* 缓存信息如下：
1) "\xac\xed\x00\x05t\x00\x06Weslie"
2) "\xac\xed\x00\x05sr\x00\x16com.example.redis.User\x89?\xc7\xfa\xa8|c^\x02\x00\x03I\x00\x03ageL\x00\x04namet\x00\x12Ljava/lang/String;L\x00\aspeciesq\x00~\x00\x01xp\x00\x00\x00\x0ct\x00\x06Wesliet\x00\x04goat"
3) "\xac\xed\x00\x05t\x00\x06Wolffy"
4) "\xac\xed\x00\x05sr\x00\x16com.example.redis.User\x89?\xc7\xfa\xa8|c^\x02\x00\x03I\x00\x03ageL\x00\x04namet\x00\x12Ljava/lang/String;L\x00\aspeciesq\x00~\x00\x01xp\x00\x00\x00\"t\x00\x06Wolffyt\x00\x04wolf"
*/
