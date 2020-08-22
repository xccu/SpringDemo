package com.example.jdbcconndemo;

import com.example.jdbcconndemo.entity.Foo;
import com.example.jdbcconndemo.entity.Voyage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/qq_20916555/article/details/80852144
@SpringBootApplication
@Slf4j
public class JdbcConnDemoApplication implements CommandLineRunner {

    //SqlServer数据源
    @Autowired
    @Qualifier("sqlServerDataSource")
    private DataSource sqlDataSource;

    //Oracle数据源
    @Autowired
    @Qualifier("oracleDataSource")
    private DataSource oracleDataSource;


    public static void main(String[] args) {
        SpringApplication.run(JdbcConnDemoApplication.class, args);
    }

    //重写run方法
    @Override
    public void run(String... args) throws Exception {
        getFOOFromDb();
        //getvodFromDb();
    }

    void getFOOFromDb(){
        try {
            showConnection(sqlDataSource);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(sqlDataSource);
            String queryStr = "select * from FOO";
            List<Foo> FooList = new ArrayList<>();
            jdbcTemplate.query(queryStr, (ResultSet resultSet) -> {
                Foo foo = Foo.builder()
                        .id(resultSet.getLong("id"))
                        .bar(resultSet.getString("bar")).build();
                FooList.add(foo);
            });
            FooList.forEach((Foo foo) -> System.out.println(foo));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    void getvodFromDb(){
        try {
            showConnection(oracleDataSource);
            JdbcTemplate jdbcTemplate = new JdbcTemplate(oracleDataSource);
            String queryStr = "select * from voyage";
            List<Voyage> vogList = new ArrayList<>();
            jdbcTemplate.query(queryStr, (ResultSet resultSet) -> {
                Voyage voyage = Voyage.builder()
                        .start(resultSet.getString("start"))
                        .end(resultSet.getString("end"))
                        .mile(resultSet.getInt("mile"))
                        .build();
                vogList.add(voyage);
            });
            vogList.forEach((Voyage voyage) -> System.out.println(voyage));
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    //显示数据库链接
    void showConnection(DataSource dataSource) throws Exception{
        System.out.println(dataSource.toString());
        Connection conn = dataSource.getConnection();
        System.out.println(conn.toString());
    }
}
