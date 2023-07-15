package com.example.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.UpdateChainWrapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class UserMapperTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSqlQuery() {

        System.out.println(("----- Sql Query test ------"));

        System.out.println(("----- Caprinae ------"));
        List<User> users = userMapper.queryByRace("Caprinae");
        users.forEach(System.out::println);

        System.out.println(("----- Lupo ------"));
        users = userMapper.queryByRace("Lupo");
        users.forEach(System.out::println);
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> users = userMapper.selectList(null);
        Assert.assertEquals(9, users.size());
        users.forEach(System.out::println);

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.in("id",1,2,3);
        users = userMapper.selectList(wrapper);
        Assert.assertEquals(3, users.size());
        users.forEach(System.out::println);
    }

    @Test
    void testInsert() {
        User user = new User();
        user.setAge(12);
        user.setSex("male");
        user.setName("test");

        userMapper.insert(user);
        Assert.assertNotNull(user.getId());
    }

    @Test
    void testUpdate(){
        User user = new User();
        user.setAge(12);
        user.setSex("male");
        user.setName("test");

        QueryWrapper wrapper=new QueryWrapper();
        wrapper.in("id",1,2,3);

        userMapper.update(user,wrapper);

        user.setId(4);
        //在调用updateById方法前，需要在T entity（对应的实体类）中的主键属性上加上@TableId注解。
        userMapper.updateById(user);
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    void testDelete(){
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.in("id",1,2);
        userMapper.delete(wrapper);


        List<Integer> ids =  new ArrayList<Integer>(Arrays.asList(3,4,5));
        userMapper.deleteBatchIds(ids);

        int count = userMapper.selectCount(null);

        Assert.assertEquals(count,0);
    }

    //https://blog.csdn.net/qq_43203949/article/details/120969281
    @Test
    public void testChain(){
        //链式查询 普通
        User user1 = new QueryChainWrapper<>(userMapper).eq("name", "Weslie").one();
        System.out.println(user1);
        // 链式查询 lambda 式。注意：不支持 Kotlin
        User user2 = new LambdaQueryChainWrapper<>(userMapper).eq(User::getName, "Wolffy").one();
        System.out.println(user2);


        User userUpdate = new User();
        userUpdate.setAge(12);
        userUpdate.setSex("male");
        userUpdate.setName("test");

        // 链式更改 普通
        new UpdateChainWrapper<>(userMapper).eq("name", "Weslie").remove();
        // 链式更改 lambda 式。注意：不支持 Kotlin
        new LambdaUpdateChainWrapper<>(userMapper).eq(User::getName, "Wolffy").update(userUpdate);

    }



}
