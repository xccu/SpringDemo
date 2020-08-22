package com.example.jdbcconndemo;

import com.example.jdbcconndemo.entity.Foo;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
class JdbcConnDemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void springDataSourceTest(){
        //输出为true
        System.out.println(dataSource instanceof HikariDataSource);
        //System.out.println(dataSource instanceof MyDataSource);
        try{
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            List<Foo> FooList = new ArrayList<>();
            if(resultSet.next()){
                Foo foo = Foo.builder()
                        .id(resultSet.getLong("id"))
                        .bar(resultSet.getString("bar")).build();
                FooList.add(foo);
            }

            System.out.println(FooList);
            statement.close();
            connection.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
