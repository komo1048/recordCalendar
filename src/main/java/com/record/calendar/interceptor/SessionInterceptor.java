package com.record.calendar.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String session = (String) request.getSession().getAttribute("loginMember");
        if (session != null){
            return true;
        }else{
            response.sendRedirect("/");
            return false;
        }
    }
}
