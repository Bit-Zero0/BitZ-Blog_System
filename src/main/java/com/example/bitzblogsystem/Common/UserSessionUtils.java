package com.example.bitzblogsystem.Common;

import com.example.bitzblogsystem.Entity.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    //得到当前的登录用户
    public static UserInfo getUser(HttpServletRequest req){
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute(AppVariable.USER_SESSION_KEY)!= null){
            return (UserInfo) session.getAttribute(AppVariable.USER_SESSION_KEY);
        }

        return null;
    }
}
