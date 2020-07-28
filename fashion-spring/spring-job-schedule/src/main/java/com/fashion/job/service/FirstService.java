package com.fashion.job.service;

import com.fashion.job.WrapperRequest;

import java.util.List;
import java.util.Map;

public interface FirstService {
    String queryName();

    Map<String,String> queryNameBatch(List<WrapperRequest<String,String>> requests);
}
