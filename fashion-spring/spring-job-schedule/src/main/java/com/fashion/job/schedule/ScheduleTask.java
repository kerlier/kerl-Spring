package com.fashion.job.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * initialDelay表示 在服务启动后多少时间才开始执行
 * fixedRate指定的是每多长时间执行一次
 *
 * cron 秒 分 时 日 月  周几
 */
@Component
public class ScheduleTask {

    @Scheduled(cron = "0/5 * * * * *")
//    @Scheduled(initialDelay=3000, fixedRate=1000)
    public void print(){
        System.out.println(System.currentTimeMillis());
    }
}
