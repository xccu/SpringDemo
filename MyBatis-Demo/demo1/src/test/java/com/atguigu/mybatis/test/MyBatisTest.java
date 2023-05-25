package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Date:2021/11/26
 * Author:ybc
 * Description:
 */
public class MyBatisTest {

    /**
     * SqlSession默认不自动提交事务，若需要自动提交事务
     * 可以使用SqlSessionFactory.openSession(true);
     */


    @Test
    public void testMyBatis() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取SqlSession 设置参数true实现自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取mapper接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //测试功能
        int result = mapper.insertUser();
        //手动提交事务
        //sqlSession.commit();
        System.out.println("result:"+result);
    }

    @Test
    public void testCRUD() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //mapper.updateUser();
        //mapper.deleteUser();
        User user = mapper.getUserById(4);
        System.out.println(user);
        int count = mapper.getCount();
        System.out.println(count);
        List<User> list = mapper.getAllUser();
        list.forEach(item -> System.out.println(item));

        List<Map<String, Object>> mapList = mapper.getAllUserToMap();
        mapList.forEach(item -> System.out.println(item));

        Map<String, Object> map = mapper.getAllUserToMap_new();

        /*List<User> users = mapper.testLike("张");
        users.forEach(item -> System.out.println(item));*/
        //map.forEach(item -> System.out.println(item));
    }

}
