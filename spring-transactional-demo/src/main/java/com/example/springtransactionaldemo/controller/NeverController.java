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
public class NeverController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 7.1.1 外部方法不开启事务
     * http://localhost:8080/d711
     * 添加失败
     */
    @RequestMapping("/d711")
    public void noTransactional_never(){
        accountService.addNever();
    }

    /**
     * 7.2.1 外部方法开启事务并抛异常
     * http://localhost:8080/d721
     * 两张表都添加失败
     */
    @RequestMapping("/d721")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_never(){
        accountService.addNever();
        scoreService.addNever();
        throw new RuntimeException();
    }

    /**
     * 7.2.2 外部方法开启事务，内部方法抛异常
     * http://localhost:8080/d722
     * 两张表都添加失败
     */
    @RequestMapping("/d722")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_never_throw(){
        accountService.addNever();
        scoreService.addNeverException();
    }

}
