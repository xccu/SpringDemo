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
public class SupportsController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 4.1.1 外部方法不开启事务，外部方法抛异常
     * http://localhost:8080/d411
     * 两张表都添加成功
     */
    @RequestMapping("/d411")
    public void noTransactional_supports(){
        accountService.addSupports();
        scoreService.addSupports();
        throw new RuntimeException();
    }

    /**
     * 4.1.2 外部方法不开启事务,内部方法抛异常
     * http://localhost:8080/d412
     * 两张表都添加成功
     */
    @RequestMapping("/d412")
    public void noTransactional_supports_throw(){
        accountService.addSupports();
        scoreService.addSupportsException();
    }

    /**
     * 4.2.1 外部方法开启事务并抛异常
     * http://localhost:8080/d421
     * 两张表都添加失败
     */
    @RequestMapping("/d421")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_supports(){
        accountService.addSupports();
        scoreService.addSupports();
        throw new RuntimeException();
    }

    /**
     * 4.2.2 外部方法开启事务，内部方法抛异常
     * http://localhost:8080/d422
     * 两张表都添加失败
     */
    @RequestMapping("/d422")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_supports_throw(){
        accountService.addSupports();
        scoreService.addSupportsException();
    }
}
