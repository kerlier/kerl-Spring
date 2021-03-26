package com.fashion.spring.redis.annotation;

import com.fashion.spring.redis.enums.RedisLockTypeEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/26 8:41
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface RedisLockAnnotation {

    /**
     * 特定参数识别，默认是第一个下标
     * @return
     */
    int lockField() default 0;

    /**
     * 超时重试次数
     * @return
     */
    int tryCount() default 3;

    /**
     * 自定义加锁类型
     * @return
     */
    RedisLockTypeEnum typeEnum();

    /**
     * 加锁时间，默认30s
     * @return
     */
    long lockTime() default 30;
}
