package com.df.config.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Jone on 2018-04-20.
 */
public class LoginInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Cookie cookies[]=request.getCookies();
        for (int i=0;i<cookies.length;i++){
            if (cookies[i].getName().equals("local_user_info"))
                return true;
        }
        response.sendRedirect("/login");
       return false;
    }
}
