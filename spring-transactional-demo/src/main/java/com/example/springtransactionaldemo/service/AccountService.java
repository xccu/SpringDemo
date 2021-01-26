package com.example.springtransactionaldemo.service;

import com.example.springtransactionaldemo.mapper.AccountMapper;
import com.example.springtransactionaldemo.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    AccountMapper accountMapper;

    public List<Account> getAll(){
        return accountMapper.selectList(null);
    }

    public void deleteAll(){
        accountMapper.delete(null);
    }

    @Transactional(propagation = Propagation.REQUIRED )
    public void addRequired(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);
    }

    @Transactional(propagation = Propagation.REQUIRED )
    public void addRequiredException(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);

        throw new RuntimeException();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW )
    public void addRequiredNew(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);
    }

    @Transactional(propagation = Propagation.NESTED )
    public void addNested(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);
    }

    @Transactional(propagation = Propagation.SUPPORTS )
    public void addSupports(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);
    }

    @Transactional(propagation = Propagation.MANDATORY )
    public void addMandatory(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);
    }

    @Transactional(propagation = Propagation.NEVER )
    public void addNever(){
        Account a = new Account(1,"u1");
        accountMapper.insert(a);
    }
}
