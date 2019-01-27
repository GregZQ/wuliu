package com.df.controller.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jone on 2019-01-26.
 */
public class CookitUtil {

    public static String getUsername(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        String username = null;
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (StringUtils.equals(cookieName,"local_user_info")){
                username = cookie.getValue();
                break;
            }
        }
        return username;
    }
}
