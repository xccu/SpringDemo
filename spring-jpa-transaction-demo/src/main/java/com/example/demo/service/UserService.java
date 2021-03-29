package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {

    @Autowired
    UserRepository userRepository; //1 可以直接注入UserRepository的Bean

    @Transactional(rollbackFor={IllegalArgumentException.class}) //2 使用Transactional注解的rollbackFor属性，指定特定异常时，数据回滚
    public User saveUserWithRollback(User user){
        User u = userRepository.save(user);
        if("Weslie".equals(user.getName())){ //3 触发异常
            log.warn("User 'Werlie' already exists, rollback");
            throw new IllegalArgumentException("User 'Werlie' already exists, rollback");
        }
        return u;
    };

    @Transactional(noRollbackFor={IllegalArgumentException.class}) //4 使用Transactional注解的rollbackFor属性，指定特定异常时，数据不回滚
    public User saveUserWithoutRollback(User user){
        User u = userRepository.save(user);
        if("Weslie".equals(user.getName())){ //5 触发异常
            log.warn("User 'Werlie' already exists, but not rollback");
            throw new IllegalArgumentException("User 'Werlie' already exists, but not rollback");
        }
        return u;
    };

    public List<User> findAll(){
        return userRepository.findAll();
    };
}
