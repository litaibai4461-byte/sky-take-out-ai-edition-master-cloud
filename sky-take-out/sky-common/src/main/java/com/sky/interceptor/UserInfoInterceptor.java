package com.sky.interceptor;

import com.sky.context.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;


public class UserInfoInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String userInfo = request.getHeader("user-info");
        if (userInfo!="" && userInfo!=null){
            BaseContext.setCurrentId(Long.valueOf(userInfo));
        }
        return true;
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception{
        BaseContext.removeCurrentId();
    }
}
