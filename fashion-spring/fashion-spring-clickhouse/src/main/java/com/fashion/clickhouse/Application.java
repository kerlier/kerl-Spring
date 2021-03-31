package com.fashion.clickhouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 9:40
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.fashion.clickhouse.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
