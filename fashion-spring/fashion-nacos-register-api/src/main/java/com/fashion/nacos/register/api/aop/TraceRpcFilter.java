package com.fashion.nacos.register.api.aop;

import com.fashion.nacos.register.api.log.TraceId;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.slf4j.MDC;

/**
 * @Author: yangyuguang
 * @Date: 2021/8/12 16:48
 */
@Activate(group = {CommonConstants.PROVIDER, CommonConstants.CONSUMER})
public class TraceRpcFilter implements Filter {
    private static final String TRACEID = "traceId";
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        boolean consumerSide = RpcContext.getContext().isConsumerSide();

        if(consumerSide){
            String s = TraceId.logTraceID.get();
            invocation.getAttachments().put(TRACEID,s);
        }else{
            String attachment = invocation.getAttachment(TRACEID);
            TraceId.logTraceID.set(attachment);
            MDC.put(TRACEID,attachment);
        }
        return invoker.invoke(invocation);
    }
}
