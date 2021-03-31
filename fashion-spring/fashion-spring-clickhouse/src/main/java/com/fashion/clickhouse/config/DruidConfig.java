package com.fashion.clickhouse.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:22
 */
@Configuration
public class DruidConfig {

    @Resource
    private JdbcParamConfig jdbcParamConfig;

    @Bean
    public DataSource dataSource(){
        System.out.println("aa:"+jdbcParamConfig.getDriverClass());
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcParamConfig.getUrl());
        druidDataSource.setDriverClassName(jdbcParamConfig.getDriverClass());
        druidDataSource.setInitialSize(jdbcParamConfig.getInitialSize());
        druidDataSource.setMinIdle(jdbcParamConfig.getMinIdle());
        druidDataSource.setMaxActive(jdbcParamConfig.getMaxActive());
        druidDataSource.setMaxWait(jdbcParamConfig.getMaxWait());
        return druidDataSource;
    }
}
