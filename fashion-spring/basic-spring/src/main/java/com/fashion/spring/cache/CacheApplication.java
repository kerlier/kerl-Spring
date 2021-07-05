package com.fashion.spring.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 17:03
 */
public class CacheApplication {

    public static void init(){
        LoadingCache<Long, String> build = CacheBuilder.newBuilder()
                .expireAfterWrite(30L, TimeUnit.MINUTES)
                .maximumSize(10000)
                .build(new CacheLoader<Long, String>() {
                    @Override
                    public String load(Long uid) throws Exception {
                        return "success";
                    }
                });
    }
}
