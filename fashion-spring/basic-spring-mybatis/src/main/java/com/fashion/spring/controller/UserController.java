package com.fashion.spring.controller;

import com.fashion.spring.pojo.User;
import com.fashion.spring.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/findUserById",method = RequestMethod.GET)
    public User findUserById(@RequestParam("id") Integer id){
        return userService.findUserById(id);
    }

    /**
     * restful 形式
     * @PathVariable这个注解，也是Spring MVC提供的，其作用是表示该变量的值是从访问路径中获取。
     * produces 表示当前返回结果是json
     * @param id
     * @return
     */
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET,produces="application/json")
    public User findUser(@PathVariable Integer id){
        return userService.findUserById(id);
    }

}
