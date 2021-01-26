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
public class NestedController {

    @Autowired
    AccountService accountService;

    @Autowired
    ScoreService scoreService;

    /**
     * 3.1.1 外部方法不开启事务，外部方法抛异常
     * http://localhost:8080/d311
     * 两张表都添加成功
     */
    @RequestMapping("/d311")
    public void noTransactional_nested(){
        accountService.addNested();
        scoreService.addNested();
        throw new RuntimeException();
    }

    /**
     * 3.1.2 外部方法不开启事务,内部方法抛异常
     * http://localhost:8080/d312
     * account添加成功，score添加失败
     */
    @RequestMapping("/d312")
    public void noTransactional_nested_throw(){
        accountService.addNested();
        scoreService.addNestedException();
    }

    /**
     * 3.2.1 外部方法开启事务并抛异常
     * http://localhost:8080/d321
     * 两张表都添加失败
     */
    @RequestMapping("/d321")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_nested(){
        accountService.addNested();
        scoreService.addNested();
        throw new RuntimeException();
    }

    /**
     * 3.2.2 外部方法开启事务，内部方法抛异常
     * http://localhost:8080/d322
     * 两张表都添加失败
     */
    @RequestMapping("/d322")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_nested_throw(){
        accountService.addNested();
        scoreService.addNestedException();
    }

    /**
     * 3.2.3 外部方法开启事务，内部方法抛异常,被外部方法捕获
     * http://localhost:8080/d323
     * account添加成功，score添加失败
     */
    @RequestMapping("/d323")
    @Transactional(propagation = Propagation.REQUIRED )
    public void transactional_nested_catch(){
        accountService.addNested();
        try {
            scoreService.addNestedException();
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }
}
