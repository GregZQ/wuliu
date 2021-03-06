package com.web.config;


import com.web.config.interceptor.LoginInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("com.web.controller")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/historydetail").setViewName("historydetail");

        registry.addViewController("/historyticket").setViewName("historyticket");

        registry.addViewController("/login").setViewName("login");

        registry.addViewController("/register").setViewName("register");

        registry.addViewController("/detail").setViewName("detail");

        registry.addViewController("/success_register").setViewName("success_register");

        registry.addViewController("/printdetail").setViewName("printdetail");

        registry.addViewController("/printticket").setViewName("printticket");

        registry.addViewController("/personal").setViewName("/personal");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns(new String[]{
                        "/login","/tologin","/register","/toregister",
                        "/img/**","/js/**","/css/**",
                        "/error","/success_register"
                });
    }
}
