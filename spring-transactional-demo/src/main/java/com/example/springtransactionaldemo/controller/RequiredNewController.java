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
public class RequiredNewController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 2.1.1 外部方法不开启事务
     * http://localhost:8080/d211
     * 两张表都添加成功
     */
    @RequestMapping("/d211")
    public void noTransactional_required_new(){
        accountService.addRequiredNew();
        scoreService.addRequiredNew();
    }

    /**
     * 2.1.1 外部方法不开启事务,外部方法抛异常
     * http://localhost:8080/d212
     * account添加成功 score添加失败
     */
    @RequestMapping("/d212")
    public void noTransactional_required_new1(){
        accountService.addRequiredNew();
        scoreService.addRequiredNewException();
    }

    /**
     * 2.2.1 外部方法开启事务并抛异常,内部方法分别开启新事务
     * http://localhost:8080/d221
     * account添加失败 score都添加成功
     */
    @RequestMapping("/d221")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_required_new_throw(){
        accountService.addRequired();
        scoreService.addRequiredNew();
        scoreService.addRequiredNew1();
        throw new RuntimeException();
    }

    /**
     * 2.2.2 外部方法开启事务并,内部方法抛异常
     * http://localhost:8080/d222
     * account添加失败 score都添加成功
     */
    @RequestMapping("/d222")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_required_new(){
        accountService.addRequired();
        scoreService.addRequiredNew();
        scoreService.addRequiredNewException();
    }

    /**
     * 2.2.3 外部方法开启事务，内部方法抛异常,被外部方法捕获
     * http://localhost:8080/d223
     * account添加失败 score中addRequiredNew添加成功,addRequiredNewException添加失败
     */
    @RequestMapping("/d223")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_required_catch(){
        accountService.addRequired();
        scoreService.addRequiredNew();
        try {
            scoreService.addRequiredNewException();
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
