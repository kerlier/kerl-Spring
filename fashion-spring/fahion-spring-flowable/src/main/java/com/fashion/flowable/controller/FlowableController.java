package com.fashion.flowable.controller;

import com.fashion.flowable.dto.Response;
import org.flowable.engine.*;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Kerl
 * @Date: 2021/5/18 9:53
 */
@RestController
public class FlowableController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ProcessEngine processEngine;


    @PostMapping("/start/{userId}/{purchaseOrderId}")
    public Response startFlow(@PathVariable String userId,
                              @PathVariable String purchaseOrderId){
        Map<String, Object> variables = new HashMap<>();
        variables.put("userId",userId);
        variables.put("purchaseOrderId",purchaseOrderId);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("OrderApproval", variables);
        String id = processInstance.getId();
        String name = processInstance.getName();
        return Response.ok();
    }
}
