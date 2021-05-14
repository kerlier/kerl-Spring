package com.fashion.spring.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/14 10:19
 */
@SpringBootApplication
@MapperScan("com.fashion.spring.mybatisplus.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
