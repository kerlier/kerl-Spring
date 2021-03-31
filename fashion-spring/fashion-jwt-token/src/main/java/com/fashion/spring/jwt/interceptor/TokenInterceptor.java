package com.fashion.spring.jwt.interceptor;

import com.fashion.spring.jwt.config.JwtConfig;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yangyuguang
 * @Date: 2021/3/31 10:30
 */
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private JwtConfig jwtConfig;

    /**
     * 拦截器，每次请求之前都会经过拦截器
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        if(requestURI.contains("/login")){
            return true;
        }
        String token = request.getHeader(jwtConfig.getHeader());
        if(StringUtils.isEmpty(token)){
            token = request.getParameter(jwtConfig.getHeader());
        }

        if(StringUtils.isEmpty(token)){
            throw new Exception(jwtConfig.getHeader()+"不能为空");
        }

        Claims tokenClaim = jwtConfig.getTokenClaim(token);
        if(tokenClaim == null || jwtConfig.isTokenExpired(tokenClaim.getExpiration())){
            throw new Exception(jwtConfig.getHeader()+"失效了");
        }
        request.setAttribute("identityId",tokenClaim.getSubject());
        return true;
    }
}
