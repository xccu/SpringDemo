package com.example.mybatispagehelperdemo.dao;

import com.example.mybatispagehelperdemo.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper //使用Mapper注解后可不使用Component注解
//@Component
public interface UserDao {

    /**
     * 使用页码和页大小
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Select("select * from Users")
    List<User> findAllByParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 使用rowBounds
     * @param rowBounds
     * @return
     */
    @Select("select * from Users")
    List<User> findAllByRowBounds(RowBounds rowBounds);

}

