package com.fashion.spring.controller;


import com.fashion.spring.ServiceException;
import com.fashion.spring.service.PropertyService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/info")
public class ReadController {

    @Resource
    private PropertyService propertyService;

    private LoadingCache<Long, String> build;

    @PostConstruct
    public void init(){
        build = CacheBuilder.newBuilder()
                .expireAfterWrite(5L, TimeUnit.SECONDS)
                .maximumSize(10000)
                .build(new CacheLoader<Long, String>() {
                    @Override
                    public String load(Long uid) throws Exception {
                        System.out.println("缓存值" + uid);
                        if(Objects.equals(uid,2L)){
                            System.out.println("报错");
                            throw new ServiceException("缓存出错");
                        }
                        return "success";
                    }
                });
    }

    @RequestMapping("/getApplicationName")
    public String getApplicationName(){
        return propertyService.getApplicationName();
    }
    @RequestMapping("/cache")
    public String getFromCache(@RequestParam Long uid){
        return build.getUnchecked(uid);
    }
}
