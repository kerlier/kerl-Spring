package com.fashion.job.controller;


import com.fashion.job.WrapperRequest;
import com.fashion.job.pojo.Person;
import com.fashion.job.service.FirstService;
import com.fashion.job.service.SecondService;
import com.fashion.job.service.TimerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.LinkedBlockingDeque;

@RestController
public class ScheduleController {


    private LinkedBlockingDeque<WrapperRequest<String,String>> queryNameQueue = new LinkedBlockingDeque<>();

    private LinkedBlockingDeque<WrapperRequest<String,Person>> queryPersonQueue = new LinkedBlockingDeque<>();


    @Autowired
    private FirstService firstService;

    @Autowired
    private SecondService secondService;

    @Autowired
    private TimerService timerService;

    @PostMapping("/getId/{id}")
    public  String queryName(@PathVariable("id") String id) throws Exception{
        WrapperRequest<String, String> request = new WrapperRequest<>();
        request.setRequest(id);
        queryNameQueue.add(request);
        return request.getFuture().get();
    }

    @PostMapping("/putId/{id}")
    public String putUid(@PathVariable("id") String id){
        timerService.putUid(id);
        return "success";
    }

    @GetMapping("/get")
    public Person queryPerson() throws Exception{
        WrapperRequest<String, Person> request = new WrapperRequest<>();
        queryPersonQueue.add(request);
        return request.getFuture().get();
    }


    @Scheduled(initialDelay = 1000, fixedRate = 10)
    public void scheduleTask(){
        int size = queryNameQueue.size();
        if(size==0){
            return;
        }

        List<WrapperRequest<String,String>> wrapperRequests = new ArrayList<>();
        for (int i=0;i< size;i++) {
            WrapperRequest<String, String> request = queryNameQueue.poll();
            wrapperRequests.add(request);
        }

        Map<String, String> result = firstService.queryNameBatch(wrapperRequests);
        result.forEach((key,value)->{
            for (int i = 0; i < wrapperRequests.size() ; i++) {
                if(Objects.equals(key,wrapperRequests.get(i).getSerialNo())){
                    wrapperRequests.get(i).getFuture().complete(value);
                    break;
                }
            }
        });
    }
}
