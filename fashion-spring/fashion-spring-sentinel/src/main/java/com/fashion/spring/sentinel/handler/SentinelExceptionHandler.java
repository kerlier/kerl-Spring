package com.fashion.spring.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;

/**
 * @Author: yangyuguang
 * @Date: 2021/5/14 9:04
 */
public class SentinelExceptionHandler {
    public static String blockExceptionHandler( BlockException e) {
        // 不同的异常返回不同的提示语
        if (e instanceof FlowException) {
//            log.info("接口限流了");
            return "请求过于频繁，请控制频率";
        } else if (e instanceof DegradeException) {
//            log.info("服务降级了");
            return "服务熔断降级了";
        } else if (e instanceof ParamFlowException) {
//            log.info("热点参数限流了");
            return "热点参数限流了";
        } else if (e instanceof SystemBlockException) {
//            log.info("触发系统保护规则");
            return "触发系统保护规则";
        } else if (e instanceof AuthorityException) {
//            log.info("触发系统保护规则");
            return "触发系统保护规则";
        }
        return "sentinel异常";
    }
}
