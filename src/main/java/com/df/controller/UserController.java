package com.df.controller;

import com.df.domain.User;
import com.df.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param username  用户名
     * @param password  密码
     * @param response
     * @param model
     * @return
     */
    @PostMapping("/tologin")
    public String login(String username, String password,
                        HttpServletResponse response, Model model){

        if (!checkLoginForm(username,password,model)){
            return "login";
        }
        User user = this.userService.findUserByUsername(username);
        if (Objects.isNull(user)||!StringUtils.equals(user.getPassword(),password)){
            model.addAttribute("username","用户名或密码不正确");
            return "login";
        }
        Cookie cookie=new Cookie("local_user_info",username);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "redirect:/openticket";
    }

    /**
     * 用户登出
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/tologout")
    public String logout(HttpServletRequest request,HttpServletResponse response){
        Cookie deleteNewCookie=new Cookie("local_user_info","admin");
        deleteNewCookie.setMaxAge(0);
        response.addCookie(deleteNewCookie);
        return "redirect:/login";
    }

    /**
     * 用户注册
     * @param username  用户名
     * @param password  密码
     * @param password2 确认密码
     * @param company 公司
     * @param model
     * @return
     */
    @PostMapping("/toregister")
    public String register(String username,String password,String password2,
                           String company,Model model){
        //表单数据检查
        if (!checkRegisterForm(username,password,password2,company,model)){
            return "register";
        }
        //补全User
        User user = User.newInstance(username,password,company);
        //插入用户
        this.userService.insert(user);
        return "redirect:/success_register";
    }

    /**
     * 检查登录表单
     * @param username
     * @param password
     * @param model
     * @return
     */
    private boolean checkLoginForm(String username, String password, Model model) {
        boolean flag = true;

        if (StringUtils.isEmpty(username)){
            model.addAttribute("username","用户名不能为空");
            flag = false;
        }

        if (StringUtils.isEmpty(password)) {
            model.addAttribute("password", "密码不能为空");
            flag = false;
        }
        return flag;
    }

    /**
     * 检查注册表单
     * @param username
     * @param password
     * @param password2
     * @param company
     * @param model
     * @return
     */
    private boolean checkRegisterForm(String username, String password, String password2,
                              String company,Model model) {
        boolean flag = true;

        if (StringUtils.isEmpty(username)){
            model.addAttribute("username","用户名不能为空");
            flag = false;
        }

        if (StringUtils.isNotEmpty(username)){
            User user = this.userService.findUserByUsername(username);
            if (Objects.nonNull(user)){
                model.addAttribute("username","用户名已被注册");
                flag =false;
            }
        }
        if (StringUtils.isEmpty(password)){
            model.addAttribute("password","密码不能为空");
            flag = false;
        }
        if (StringUtils.isEmpty(password2)|| !StringUtils.equals(password,password2)){
            model.addAttribute("password2","重复密码不一致");
            flag = false;
        }
        if (StringUtils.isEmpty(company)){
            model.addAttribute("company","公司信息不能为空");
            flag = false;
        }
        return flag;
    }
}
