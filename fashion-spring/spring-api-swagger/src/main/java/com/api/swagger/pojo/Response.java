package com.api.swagger.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "用户返回结果类",description = "多个controller返回结果")
public class Response<T> implements Serializable {

    @ApiModelProperty(example = "200")
    private String code;

    @ApiModelProperty(example = "成功")
    private String msg;

    @ApiModelProperty(example = "success")
    private T data;

    public Response(T data){
        this.code = "200";
        this.msg ="成功";
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
