package com.example.bitzblogsystem.Config;

import com.example.bitzblogsystem.Common.AppVariable;
import com.example.bitzblogsystem.Entity.UserInfo;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * true -> 用户已登录
     * false -> 用户未登录
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        // 用户已登陆
        if(session != null && session.getAttribute(AppVariable.USER_SESSION_KEY)!= null){
            System.out.println("当前登录的用户为: "
                    + ((UserInfo) session.getAttribute(AppVariable.USER_SESSION_KEY)).getUsername());
            return true;
        }

        // 调整到登录页面
        response.sendRedirect("/login.html");
        return false;
    }
}
