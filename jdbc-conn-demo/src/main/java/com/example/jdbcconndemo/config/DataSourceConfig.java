package com.example.jdbcconndemo.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    //自定义数据源1
    @Bean("oracleDataSource")
    @ConfigurationProperties(prefix = "oracle.datasource")
    public DataSource getOracleDataSource() {
        return DataSourceBuilder.create().type(BasicDataSource.class).build();
    }

    //自定义数据源2
    @Bean("sqlServerDataSource")
    @ConfigurationProperties(prefix = "sqlserver.datasource")
    public DataSource getSqlServerDataSource() {
        return DataSourceBuilder.create().type(BasicDataSource.class).build();
    }
}
