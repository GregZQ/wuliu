package com.df.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {
    @Value("${user.username}")
    private String username;
    @Value("${user.password}")
    private String password;


    @PostMapping("/tologin")
    public String login(String username, String password, HttpServletResponse response, Model model){
        if (username!=null&&!username.trim().equals("")
                &&password!=null&&!password.trim().equals("")){
            if (username.equals(this.username)&&password.equals(this.password)){
                Cookie cookie=new Cookie("local_user_info",username);
                cookie.setPath("/");
                response.addCookie(cookie);

                return "redirect:/openticket";
            }
        }
        model.addAttribute("msg","登录信息有误,请重新输入");
        return "login";
    }

    @GetMapping("/tologout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Cookie deleteNewCookie=new Cookie("local_user_info","admin");
        deleteNewCookie.setMaxAge(0);
        response.addCookie(deleteNewCookie);
        return "redirect:/login";
    }
}
