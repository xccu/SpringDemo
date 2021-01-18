package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class MybatisPlusConfig {

    /**
     *   mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor page = new PaginationInterceptor();
        //page.setMaxLimit((long)2);
        //page.setDialectType("h2");
        return page;
    }

    /**
     * 自定义数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    public DataSource getSqlServerDataSource() {
        DataSource dt= DataSourceBuilder.create().type(BasicDataSource.class).build();
        return dt;
    }
}
