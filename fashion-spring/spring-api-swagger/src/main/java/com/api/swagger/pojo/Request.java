package com.api.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@ApiModel("swagger controller请求类")
public class Request implements Serializable {

    @NotNull
    @ApiModelProperty(name = "客户姓名",example = "kerl", required = true)
    private String personName;

    @ApiModelProperty(name = "客户联系方式",example = "111")
    private String telephone;

    @ApiModelProperty(name = "客户年龄",example = "0")
    private Integer age;


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
