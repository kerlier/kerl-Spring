package com.fashion.nacos.register.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;

//@Activate(group = {CommonConstants.PROVIDER,CommonConstants.CONSUMER})
public class MyOwnFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {

        boolean providerSide = RpcContext.getContext().isProviderSide();




        return null;
    }
}
