package com.seckill.controller;

import com.seckill.service.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/good")
@RestController
public class GoodController {

    @Autowired
    private GoodService goodService;

    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @PostMapping("/{id}/{count}")
    public String seckillGood(@PathVariable("id") Integer id, @PathVariable("count")Integer count){
        return goodService.sale(id,count)? "购买成功":"购买失败";
    }
    
    @PostMapping("redis/{id}/{count}")
    public String seckillGoodRedis(@PathVariable("id") Integer id, @PathVariable("count")Integer count){
        return goodService.saleRedis(id,count)? "购买成功":"购买失败";
    }
}
