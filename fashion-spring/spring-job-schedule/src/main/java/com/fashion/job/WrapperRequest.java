package com.fashion.job;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class WrapperRequest<T,R> {

    private String serialNo;

    private T request;

    private CompletableFuture<R> future;

    public WrapperRequest(){
        this.future = new CompletableFuture<>();
        this.serialNo = UUID.randomUUID().toString();
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public CompletableFuture<R> getFuture() {
        return future;
    }

    public void setFuture(CompletableFuture<R> future) {
        this.future = future;
    }
}
