package com.fashion.nacos.register.api.aop;

import com.fashion.nacos.register.api.log.IdWorker;
import com.fashion.nacos.register.api.log.TraceId;
import org.slf4j.MDC;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: yangyuguang
 * @Date: 2021/8/12 16:29
 */
@ConditionalOnClass(value = {HttpServletRequest.class, OncePerRequestFilter.class})
@Order(value = -1)
public class TraceIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        IdWorker worker = new IdWorker(1,1,1);
        String traceId=String.valueOf(worker.nextId());
        TraceId.logTraceID.set(traceId);
        MDC.put("traceId", traceId);
        System.out.println("生成唯一Id：" + traceId);
        filterChain.doFilter(request, response);

    }
}
