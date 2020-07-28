package com.seckill.pojo;

import java.io.Serializable;

public class GoodOrder implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 307298236832108418L;

	private Integer orderId;

    private Integer goodId;

    private Integer count;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}