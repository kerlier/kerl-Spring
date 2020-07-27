package com.api.swagger.controller;

import com.api.swagger.pojo.Request;
import com.api.swagger.pojo.Response;
import com.api.swagger.version.ApiVersion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;


@Api("swagger控制器")
@RestController("/greet")
public class SwaggerController {

    @ApiOperation(value = "greeting of swagger")
    @GetMapping(value = "/v1.0")
    @ApiVersion(group = {"v1.0"})
    public String greetSwagger(){
        return "hello swagger api";
    }

    @ApiOperation(value = "greeting of swagger")
    @GetMapping(value = "/v1.1")
    @ApiVersion(group = {"v1.1"})
    public String greetSwagger1(){
        return "hello swagger api1";
    }

    @ApiOperation(value = "greet for person")
    @ApiParam(name = "/name/v1.0",defaultValue = "world",required=true)
    @GetMapping(value = "/v1.0/greetPerson")
    @ApiVersion(group = {"v1.0"})
    public String greet(String personName){
        return "hello " + personName;
    }

    @ApiOperation(value = "greet for person with pojo request")
    @PostMapping(value = "/v1.0/pojo")
    @ApiVersion(group = {"v1.0"})
    public String greet3(@ApiParam(name = "用户请求类",required = true) @RequestBody Request request){
        return "Hello "+ request.getPersonName() + request.getTelephone() + request.getAge();
    }

    @ApiOperation(value = "greet for person with pojo request")
    @PostMapping(value = "/v1.0/person3")
    @ApiVersion(group = {"v1.0"})
    public Response<String> greet4(@ApiParam(name = "用户请求类",required = true) @RequestBody Request request){
        return new Response("Hello "+ request.getPersonName() + request.getTelephone() + request.getAge());
    }

}
