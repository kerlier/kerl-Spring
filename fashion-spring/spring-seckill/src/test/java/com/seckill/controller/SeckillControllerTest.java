package com.seckill.controller;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.seckill.mapper.GoodMapper;
import com.seckill.pojo.Good;
import com.seckill.service.GoodService;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:application.yml")
public class SeckillControllerTest {
    private static Integer USER_NUM=1000;
    private static Integer SALE_NUM=3;
    private static Integer saleOutNum =0;
    private static Integer userNum =0;

    private static Integer GOOD_ID=1;

    public static CountDownLatch countDownLatch = new CountDownLatch(USER_NUM);
    
    private static CyclicBarrier  cyclicBarrier = new CyclicBarrier(USER_NUM+1);

    @Autowired
    private GoodService goodService;
    
    @Autowired
    private GoodMapper goodMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Before
    public void init() {
    	stringRedisTemplate.opsForValue().set("test", "100");
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void testRedisHash() {
    	
    	Good good =(Good) redisTemplate.boundHashOps("good").get("good_1");
    	
    	System.out.println(good.getCount());
    }
    
    @Test
    public void testMapper() {
//    	int updateAmountVersion = goodMapper.updateAmountVersion(1, 4, 59);
//    	System.out.println(updateAmountVersion);
    	boolean sale = goodService.sale(1, 3);
    	System.out.println(sale?"购买成功":"成功失败");
    	
    	boolean sale1= goodService.sale(1, 3);
    	System.out.println(sale1?"购买成功":"成功失败");
    }
    
    @Test
    public void testRedis() {
        for (int i = 0; i < USER_NUM ; i++) {
            new Thread(new Use1Runnable()).start();
            countDownLatch.countDown();
        } 
        try {
            Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}
        String string = stringRedisTemplate.opsForValue().get("test");
        System.out.println(string);
    }
    /**
     * 使用悲观锁： 1193
     * 使用version乐观锁：419，速度比悲观所快了很多
     * 使用redis乐观锁：
     */
    @Test
    public void testPurchase(){
    	long beginTime = System.currentTimeMillis();
        for (int i = 0; i < USER_NUM ; i++) {
            new Thread(new UseRunnable()).start();
            countDownLatch.countDown();
        } 
		try {
			cyclicBarrier.await();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
    	long endTime = System.currentTimeMillis();
    	System.out.println("花费时间:" + (endTime-beginTime));
        System.out.println("卖出去的数量: "+ saleOutNum);
        System.out.println("卖出去的人数: "+ userNum);
    }

    public class UseRunnable implements Runnable{
        @Override
        public void run() {
            try {
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }

            boolean sale = goodService.sale(GOOD_ID, SALE_NUM);
            if(sale){
            	synchronized (countDownLatch) {
            		 saleOutNum += SALE_NUM;
                     userNum+=1;
				}
            }
            
            try {
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
        }
    }
    
    public class Use1Runnable implements Runnable{
        @Override
        public void run() {
            try {
                countDownLatch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                Long increment = stringRedisTemplate.opsForValue().increment("test", -1);
                if(increment<0) {
                	stringRedisTemplate.opsForValue().increment("test", 1);
                }
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
    }

}
