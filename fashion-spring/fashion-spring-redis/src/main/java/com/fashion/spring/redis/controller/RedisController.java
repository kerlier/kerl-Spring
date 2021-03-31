package com.fashion.spring.redis.controller;

import com.fashion.spring.redis.annotation.RedisLockAnnotation;
import com.fashion.spring.redis.enums.RedisLockTypeEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Book;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/26 9:42
 */
@RestController
public class RedisController {

    @GetMapping("/getRedis")
    @RedisLockAnnotation(typeEnum = RedisLockTypeEnum.ONE, lockTime = 3)
    public Book testRedisLock(@RequestParam("userId") Long userId) {
        try {
            System.out.println("睡眠执行前");
            Thread.sleep(10000);
            System.out.println("睡眠执行后");
        } catch (Exception e) {
            // log error
            System.out.println("controller has error");
        }
        return null;
    }
}
