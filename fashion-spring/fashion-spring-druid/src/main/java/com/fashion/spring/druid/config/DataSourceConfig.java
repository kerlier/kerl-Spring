package com.fashion.spring.druid.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @Author: yangyuguang
 * @Date: 2021/4/1 8:48
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private DruidConfig druidConfig;

    @Bean
    public DruidDataSource dataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(druidConfig.getUrl());
        druidDataSource.setUsername(druidConfig.getUsername());
        druidDataSource.setPassword(druidConfig.getPassword());
        druidDataSource.setDriverClassName(druidConfig.getDriverClassName());
        druidDataSource.setInitialSize(druidConfig.getInitialSize());
        druidDataSource.setMinIdle(druidConfig.getMinIdle());
        druidDataSource.setMaxActive(druidConfig.getMaxActive());
        try{
            druidDataSource.setFilters(druidConfig.getFilters());
        }catch (Exception e){
            e.printStackTrace();
        }
        return druidDataSource;
    }

    @Bean(name="druidTemplate")
    public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean srb =
                new ServletRegistrationBean(new StatViewServlet(),"/druid/*");
        //设置控制台管理用户
        srb.addInitParameter("loginUsername","root");
        srb.addInitParameter("loginPassword","root");
        //是否可以重置数据
        srb.addInitParameter("resetEnable","false");
        return srb;
    }
}
