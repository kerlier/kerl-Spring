package com.seckill.service.impl;

import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.seckill.mapper.GoodMapper;
import com.seckill.mapper.GoodOrderMapper;
import com.seckill.pojo.Good;
import com.seckill.pojo.GoodExample;
import com.seckill.pojo.GoodOrder;
import com.seckill.service.GoodService;

@Service
public class GoodServiceImpl implements GoodService {
	
	public static final String GOOD_PREFIX = "good_";

    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private GoodOrderMapper orderMapper;
    
    @Autowired
    private StringRedisTemplate redisTemplate;
    
    @PostConstruct
    public void init() {
    	redisTemplate.setEnableTransactionSupport(true);
    	List<Good> goods = goodMapper.selectByExample(new GoodExample());
    	for (Good good : goods) {
    		redisTemplate.opsForValue().set(GOOD_PREFIX+ good.getGoodId(),good.getCount()+"");
    		System.out.println("存的值为：" +GOOD_PREFIX+ good.getGoodId()+"---" +redisTemplate.opsForValue().get(GOOD_PREFIX+ good.getGoodId()));
		}
    }

	@Override
    public boolean sale(Integer goodId, Integer buys) {
    	
    	Good good = goodMapper.selectByPrimaryKey(goodId);
    	
    	if(good.getCount()- buys <0) {
    		return false;
    	}
    	
        if(goodMapper.updateAmountVersion(goodId,buys,good.getVersion())>0) {
        	GoodOrder goodOrder = new GoodOrder();
        	goodOrder.setGoodId(goodId);
        	goodOrder.setCount(buys);
        	orderMapper.insertSelective(goodOrder);
        	return true;
        }
        waitForLock();
        return sale(goodId,buys);
    }
    
	/**
	 * 使用redis乐观锁进行秒杀系统
	 */
	@Override
	public boolean saleRedis(Integer goodId, Integer buys) {
		//使用watch
		redisTemplate.watch(GOOD_PREFIX+ goodId);
		try {
			String numString = redisTemplate.opsForValue().get(String.valueOf(GOOD_PREFIX + goodId));
			
			redisTemplate.multi();
			if(null!=numString) {
				int stock = Integer.parseInt(numString);
				stock = stock-1;
				if(stock<0) {
					return false;
				}
				redisTemplate.opsForValue().set(GOOD_PREFIX+ goodId, stock+"");
			}
//			redisTemplate.opsForValue().increment(GOOD_PREFIX+ goodId, -buys);
			
			List<Object> exec = redisTemplate.exec();
			if(exec.isEmpty()) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			redisTemplate.unwatch();
		}
		
		return true;
	}

    private void waitForLock() {
    	try {
			Thread.sleep(new Random().nextInt(5)+1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
}
