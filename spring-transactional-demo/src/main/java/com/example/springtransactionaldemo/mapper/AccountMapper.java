package com.example.springtransactionaldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springtransactionaldemo.model.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountMapper extends BaseMapper<Account> {
}
