package com.fashion.job.service.impl;

import com.fashion.job.service.TimerService;
import com.fashion.job.timer.Task;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: yangyuguang
 * @Date: 2021/4/22 15:49
 */
@Service
public class TimerServiceImpl implements TimerService {

    private Map<String,Integer> idIndexMap = new HashMap<>();

    private static Set[] solts = new Set[30];

    private Integer timer = 0;

    static {
        for (int i = 0; i < solts.length; i++) {
            solts[i] = new HashSet();
        }
    }

    private Integer currentIndex = 0;

    @Override
    public void putUid(String uid) {
        //如果为Null的话,表示这个uid还没有存入到slot中
        Integer index = idIndexMap.get(uid);
        if(Objects.nonNull(index)){
            solts[index].remove(uid);
        }

        Task task = new Task();
        task.setUid(uid);
        task.setExpireTimeStamp(new Date().getTime()+3000L);

        if(currentIndex==0){
            solts[currentIndex].add(task);
            idIndexMap.put(uid, currentIndex);
            System.out.println("currentIndex:" + currentIndex +", slots:" +solts[currentIndex] );
        }else{
            solts[currentIndex-1].add(task);
            idIndexMap.put(uid, currentIndex-1);
            System.out.println("currentIndex:" + (currentIndex-1) +", slots:" +solts[currentIndex-1] );
        }
    }


    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    public void scheduleTask(){
        solts[currentIndex].removeIf(o->{
            Task task = (Task) o;
            if(task.getExpireTimeStamp()<= new Date().getTime()){
                System.out.println("去除数据:" + task.getUid());
                return true;
            }
            return false;
        });

        currentIndex += 1;
        System.out.println("当前index:" + currentIndex);
        if(currentIndex == 30){
            //已经超时的数据是
            currentIndex = 0;
        }
//        if(timer % 30 ==0){
//           Set set = solts[currentIndex];
//           System.out.println("当前数据:" + set+",当前index:"+ currentIndex);
//           for (Object o : set) {
//               System.out.println("已经超时的数据是:" + o);
//           }
//           set.clear();
//        }
    }
}
