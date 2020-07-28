package com.fashion.job.service.impl;

import com.fashion.job.WrapperRequest;
import com.fashion.job.service.FirstService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FirstServiceImpl implements FirstService {
    @Override
    public String queryName() {
        return "first service";
    }


    public Map<String,String> queryNameBatch(List<WrapperRequest<String,String>> requests){
        Map<String, String> results = new HashMap<>();

        //这里应该使用request的批处理方式
        for (WrapperRequest request:  requests) {
            results.put(request.getSerialNo(),"result-"+ request.getSerialNo());
        }
        return results;
    }
}
