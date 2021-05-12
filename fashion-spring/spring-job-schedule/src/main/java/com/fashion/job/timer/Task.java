package com.fashion.job.timer;

/**
 * @Author: yangyuguang
 * @Date: 2021/4/23 14:21
 */
public class Task {

    private String uid;

    private Long expireTimeStamp;

    public Task(){
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getExpireTimeStamp() {
        return expireTimeStamp;
    }

    public void setExpireTimeStamp(Long expireTimeStamp) {
        this.expireTimeStamp = expireTimeStamp;
    }
}
