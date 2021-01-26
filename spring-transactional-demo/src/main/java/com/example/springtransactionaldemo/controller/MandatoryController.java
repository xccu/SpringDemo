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
public class MandatoryController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 6.1.1 外部方法不开启事务
     * http://localhost:8080/d611
     * 添加失败
     */
    @RequestMapping("/d611")
    public void noTransactional_mandatory(){
        accountService.addMandatory();
    }

    /**
     * 6.2.1 外部方法开启事务并抛异常
     * http://localhost:8080/d621
     * 两张表都添加失败
     */
    @RequestMapping("/d621")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_mandatory(){
        accountService.addMandatory();
        scoreService.addMandatory();
        throw new RuntimeException();
    }

    /**
     * 6.2.2 外部方法开启事务，内部方法抛异常
     * http://localhost:8080/d622
     * 两张表都添加失败
     */
    @RequestMapping("/d622")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_mandatory_throw(){
        accountService.addMandatory();
        scoreService.addMandatoryException();
    }

}
