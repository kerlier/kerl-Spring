package com.fashion.flowable.dto;

/**
 * @Author: Kerl
 * @Date: 2021/5/18 9:55
 */
public class Response {

    private Integer code;

    private String message;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static Response ok(){
        Response response = new Response();
        response.setCode(200);
        return response;
    }
}
