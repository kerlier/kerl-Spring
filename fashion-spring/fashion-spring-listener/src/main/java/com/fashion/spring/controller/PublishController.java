package com.fashion.spring.controller;

import com.fashion.spring.config.ConfigDTO;
import com.fashion.spring.event.FashionEvent;
import com.fashion.spring.pulisher.MyOwnPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: yangyuguang
 * @Date: 2021/7/1 10:27
 */
@RestController
@RequestMapping("/publish")
public class PublishController {

    @Autowired
    private MyOwnPublisher publisher;

    @GetMapping
    public String publish(@RequestParam("dataId") String dataId,
                          @RequestParam("group") String group){
        ConfigDTO configDTO = new ConfigDTO();
        configDTO.setDataId(dataId);
        configDTO.setGroup(group);
        FashionEvent event = new FashionEvent(configDTO);
        publisher.publishEvent(event);
        return "success";
    }
}
