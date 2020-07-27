package com.api.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("person类")
public class Person {
    @ApiModelProperty(name = "客户Id",example = "001",required = true)
    private String id;

    @ApiModelProperty(name = "用户姓名",example = "kerl",required = true)
    private String personName;

    @ApiModelProperty(name="用户年龄",example = "20")
    private String age;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
