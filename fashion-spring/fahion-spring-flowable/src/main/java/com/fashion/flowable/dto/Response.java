package com.fashion.flowable.dto;

/**
 * @Author: Kerl
 * @Date: 2021/5/18 9:55
 */
public class Response<T> {

    private T result;

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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public static Response ok(){
        Response response = new Response();
        response.setCode(200);
        return response;
    }

    public static <T> Response ok(T result){
        Response response = new Response();
        response.setCode(200);
        response.setResult(result);
        return response;
    }
}
