package com.api.swagger.controller;

import com.api.swagger.pojo.Request;
import com.api.swagger.pojo.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


@Api("swagger控制器")
@RestController
public class SwaggerController {

    @ApiOperation(value = "greeting of swagger")
    @GetMapping(value = "/greet")
    public String greetSwagger(){
        return "hello swagger api";
    }

    @ApiOperation(value = "greet for person")
    @ApiParam(name = "personName",defaultValue = "world",required=true)
    @GetMapping(value = "greetPerson")
    public String greet(String personName){
        return "hello " + personName;
    }

    @ApiOperation(value = "greet for person with pojo request")
    @PostMapping(value = "greetPerson2")
    public String greet3(@ApiParam(name = "用户请求类",required = true) @RequestBody Request request){
        return "Hello "+ request.getPersonName() + request.getTelephone() + request.getAge();
    }


    @ApiOperation(value = "greet for person with pojo request")
    @PostMapping(value = "greetPerson3")
    public Response<String> greet4(@ApiParam(name = "用户请求类",required = true) @RequestBody Request request){
        return new Response("Hello "+ request.getPersonName() + request.getTelephone() + request.getAge());
    }


}
