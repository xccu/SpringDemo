package com.example.springtransactionaldemo.controller;

import com.example.springtransactionaldemo.service.AccountService;
import com.example.springtransactionaldemo.service.ScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NotSupportedController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 5.1.1 外部方法不开启事务，外部方法抛异常
     * http://localhost:8080/d511
     * 两张表都添加成功
     */
    @RequestMapping("/d511")
    public void noTransactional_notSupported(){
        accountService.addRequired();
        scoreService.addNotSupported();
        throw new RuntimeException();
    }

    /**
     * 5.1.2 外部方法不开启事务,内部方法抛异常
     * http://localhost:8080/d512
     * 两张表都添加成功
     */
    @RequestMapping("/d512")
    public void noTransactional_notSupported_throw(){
        accountService.addRequired();
        scoreService.addNotSupportedException();
    }

    /**
     * 5.2.1 外部方法开启事务并抛异常
     * http://localhost:8080/d521
     * account添加失败,score添加成功
     */
    @RequestMapping("/d521")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_notSupported(){
        accountService.addRequired();
        scoreService.addNotSupported();
        throw new RuntimeException();
    }

    /**
     * 5.2.2 外部方法开启事务，内部方法抛异常
     * http://localhost:8080/d522
     * account添加失败,score添加成功
     */
    @RequestMapping("/d522")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_notSupported_throw(){
        accountService.addRequired();
        scoreService.addNotSupportedException();
    }
}
