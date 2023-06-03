package com.example.bitzblogsystem.Config;

import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//拦截器
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/editor.md/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/login.html")
                .excludePathPatterns("/reg.html")
                .excludePathPatterns("/blog_list.html")
                .excludePathPatterns("/blog_content.html")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/reg");
    }
}
