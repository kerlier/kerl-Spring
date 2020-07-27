package com.api.swagger.controller;

import com.api.swagger.pojo.Person;
import com.api.swagger.version.ApiVersion;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现兼容多版本的控制
 * springboot实现多版本控制两种方法： 1.url显式调用版本号 2.使用content-type
 *
 * 这里使用第一种，显式调用版本号
 *
 */

@Api(value = "urlVersionController")
@RestController("/person")
public class UrlVersionController {

    private Map<String,Person> personMap = new HashMap<>();

    @PostMapping({"/v1.0/add","/v1.1/add"})
    @ApiVersion(group = {"v1.0","v1.1"})
    public Person add(@RequestBody Person person){
        personMap.put(person.getId(),person);
        return person;
    }

    @Deprecated
    @GetMapping("/v1.0/{id}")
    @ApiVersion(group = {"v1.0"})
    public Person get(@PathVariable("id") String id){
        return personMap.get(id);
    }
    @GetMapping("/v1.1/{id}")
    @ApiVersion(group = {"v1.1"})
    public Person getPerson(@PathVariable("id") String id){
        Person person = personMap.get(id);
        if(null != person){
            person.setPersonName("v1.1");
        }
        return person;
    }
}
