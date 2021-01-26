package com.example.springtransactionaldemo.controller;

import com.example.springtransactionaldemo.service.ScoreService;
import com.example.springtransactionaldemo.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class RequiredController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 删除所有数据
     * http://localhost:8080/delete
     */
    @RequestMapping("/delete")
    public void delete_all(){
        accountService.deleteAll();
        scoreService.deleteAll();
    }

    /**
     * 1.1.1 外部方法不开启事务，外部方法抛异常
     * http://localhost:8080/d111
     * 两张表都添加成功
     */
    @RequestMapping("/d111")
    public void noTransactional_required(){
        accountService.addRequired();
        scoreService.addRequired();
        throw new RuntimeException();
    }

    /**
     * 1.1.2 外部方法不开启事务,内部方法抛异常
     * http://localhost:8080/d112
     * 两张表都添加成功
     */
    @RequestMapping("/d112")
    public void noTransactional_required_throw(){
        accountService.addRequired();
        scoreService.addRequiredException();
    }

    /**
     * 1.2.1 外围方法开启事务，外围方法抛异常
     * http://localhost:8080/d121
     * 两张表都添加失败
     */
    @RequestMapping("/d121")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_required(){
        accountService.addRequired();
        scoreService.addRequired();
        throw new RuntimeException();
    }

    /**
     * 1.2.2 外围方法开启事务，内部方法抛异常
     * http://localhost:8080/d122
     * 两张表都添加失败
     */
    @RequestMapping("/d122")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_required_throw(){
        accountService.addRequired();
        scoreService.addRequiredException();
    }

    /**
     * 1.2.3 外围方法开启事务，内部方法抛异常,被外部方法捕获
     * http://localhost:8080/d123
     * 两张表都添加失败
     */
    @RequestMapping("/d123")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_required_catch(){
        accountService.addRequired();
        try {
            scoreService.addRequiredException();
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

}
