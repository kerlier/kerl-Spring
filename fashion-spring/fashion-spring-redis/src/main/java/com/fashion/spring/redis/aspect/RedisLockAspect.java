package com.fashion.spring.redis.aspect;

import com.fashion.spring.redis.annotation.RedisLockAnnotation;
import com.fashion.spring.redis.enums.RedisLockTypeEnum;
import com.fashion.spring.redis.queue.RedisLockDefinitionHolder;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.sql.Time;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/26 8:46
 *
 * 队列的作用是为了进行延时，以防方法执行时间过长，导致报错从而释放了锁，
 * 所以我们这边加一个队列进行续时
 */

@Aspect
@Component
public class RedisLockAspect{

    private static Logger LOGGER = LoggerFactory.getLogger(RedisLockAspect.class);

    @Autowired
    private RedisTemplate redisTemplate;

    private ConcurrentLinkedQueue<RedisLockDefinitionHolder> holderList =  new ConcurrentLinkedQueue();

    /**
     * 定义切面
     */
    @Pointcut("@annotation(com.fashion.spring.redis.annotation.RedisLockAnnotation)")
    public void redisLockPC() {
    }

    /**
     * 定义时机
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around(value = "redisLockPC()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable{

        Method method = resolveMethod(pjp);
        System.out.println("执行切面");
        RedisLockAnnotation annotation = method.getAnnotation(RedisLockAnnotation.class);
        //获取锁类型
        RedisLockTypeEnum redisLockTypeEnum = annotation.typeEnum();
        //获取参数
        Object[] args = pjp.getArgs();

        String ukString = args[annotation.lockField()].toString();

        String businessKey  = redisLockTypeEnum.getUniqueKey(ukString);

        String uniqueValue  = UUID.randomUUID().toString();

        Object result = null;

        try{
            //进行加锁
            boolean isSuccess = redisTemplate.opsForValue().setIfAbsent(businessKey, uniqueValue);
            if(!isSuccess){
                throw new Exception("You can not do it,because another has get the lock");
            }
            redisTemplate.expire(businessKey,annotation.lockTime(), TimeUnit.SECONDS);
            Thread thread = Thread.currentThread();

            //将当前Task放到队列中
            holderList.add(new RedisLockDefinitionHolder(businessKey,annotation.lockTime(),
                    System.currentTimeMillis(),thread, annotation.tryCount()));

            //这里执行的时间比较长，需要等待10秒，然后这期间会在队列里面执行
            result = pjp.proceed();

            //线程被中断，抛出异常
            if(thread.isInterrupted()){
                throw new InterruptedException("You had been interrupted ==");
            }
        }catch (InterruptedException e){
            //如果超时的话
            System.out.println("超时报错");
            throw new Exception("Interrupt exception, please send request again");
        }catch (Exception e){
            System.out.println("错误");
            LOGGER.error("has some error, please check again", e);
        }finally {
            System.out.println("释放锁");
            redisTemplate.delete(businessKey);
            LOGGER.info("release the lock, businessKey is ["+ businessKey+"]");
        }

        return result;
    }


    public Method resolveMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature signature = pjp.getSignature();
        MethodSignature msig = null;
        if(!(signature instanceof  MethodSignature)){
            throw new IllegalArgumentException("该注解只能用于方法上");
        }

        msig =  (MethodSignature)signature;
        Object target = pjp.getTarget();
        Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        return method;
    }

    private static final ScheduledExecutorService SCHEDULER = new ScheduledThreadPoolExecutor(1,
            new BasicThreadFactory.Builder().namingPattern("redisLock-schedule-pool").daemon(true).build());
    {
        // 两秒执行一次「续时」操作
        SCHEDULER.scheduleAtFixedRate(() -> {
            // 这里记得加 try-catch，否者报错后定时任务将不会再执行=-=
            Iterator<RedisLockDefinitionHolder> iterator = holderList.iterator();
            while (iterator.hasNext()) {
                RedisLockDefinitionHolder holder = iterator.next();
                // 判空
                if (holder == null) {
                    System.out.println("holder为空，删除");
                    iterator.remove();
                    continue;
                }
                // 判断 key 是否还有效，无效的话进行移除
                if (redisTemplate.opsForValue().get(holder.getBusinessKey()) == null) {
                    System.out.println("getBusinessKey为空，删除");
                    iterator.remove();
                    continue;
                }
                // 超时重试次数，超过时给线程设定中断
                if (holder.getCurrentCount() > holder.getTryCount()) {
                    holder.getCurrentTread().interrupt();
                    iterator.remove();
                    continue;
                }
                // 判断是否进入最后三分之一时间
                long curTime = System.currentTimeMillis();
                boolean shouldExtend = (holder.getLastModifyTime() + holder.getModifyPeriod()) <= curTime;
                if (shouldExtend) {
                    holder.setLastModifyTime(curTime);
                    redisTemplate.expire(holder.getBusinessKey(), holder.getLockTime(), TimeUnit.SECONDS);
                    LOGGER.info("businessKey : [" + holder.getBusinessKey() + "], try count : " + holder.getCurrentCount());
                    holder.setCurrentCount(holder.getCurrentCount() + 1);
                }
            }
        }, 0, 2, TimeUnit.SECONDS);
    }
}
