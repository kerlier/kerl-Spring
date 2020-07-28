package com.seckill.service;

public interface GoodService {
    boolean sale(Integer goodId,Integer buys);
    
    boolean saleRedis(Integer goodId,Integer buys);
}
