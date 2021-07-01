package com.spring.xxl.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * @Author: yangyuguang
 * @Date: 2021/6/25 8:40
 */
@RestController
public class ScheduleController {
    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    private ScheduledFuture<?> future;

    private Long nowTimeStamp = new Date().getTime();

    private static final String DEFAULT_CRON = "0/5 * * * * ?";

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @PostConstruct
    public void init(){
        setCron(DEFAULT_CRON);
    }

    @GetMapping("/setCron")
    public void setCron2(String cron){
        System.out.println("当前cron:" + cron);
        setCron(cron);
    }

    public void setCron(String cron) {
        stopCron();
        future = threadPoolTaskScheduler.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    long time = new Date().getTime();
                    long l = time - nowTimeStamp;
                    nowTimeStamp = time;
                    System.out.println("执行间隔:" + (l/1000) +"'s");
                    System.out.println("Msg:定时生成文件成功");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Error:定时生成文件错误");
                }
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                if("".equals(cron) || cron == null){
                    return null;
                }
                CronTrigger trigger = new CronTrigger(cron);// 定时任务触发，可修改定时任务的执行周期
                Date nextExecDate = trigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }


    public void stopCron() {
        if (future != null) {
            future.cancel(true);//取消任务调度
        }
    }
}
