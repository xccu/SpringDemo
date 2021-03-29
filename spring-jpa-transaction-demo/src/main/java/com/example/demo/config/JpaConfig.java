package com.example.demo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JpaConfig {

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
