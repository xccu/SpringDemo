package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     * @return
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
    @Bean(name = "datasource")
    @ConfigurationProperties(prefix = "spring.datasource.h2")
    public DataSource getDataSource() {
        DataSource dt= DataSourceBuilder.create().type(BasicDataSource.class).build();
        return dt;
    }
}
