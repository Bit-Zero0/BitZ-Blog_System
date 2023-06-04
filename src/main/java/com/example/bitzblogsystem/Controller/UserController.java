package com.example.bitzblogsystem.Controller;

import com.example.bitzblogsystem.Common.AjaxResult;
import com.example.bitzblogsystem.Common.AppVariable;
import com.example.bitzblogsystem.Common.UserSessionUtils;
import com.example.bitzblogsystem.Entity.UserInfo;
import com.example.bitzblogsystem.Entity.VO.UserInfoVO;
import com.example.bitzblogsystem.Service.ArticleServie;
import com.example.bitzblogsystem.Service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleServie articleServie;

    @RequestMapping("/reg")
    public AjaxResult reg(UserInfo userInfo) {
        if(userInfo == null || !StringUtils.hasLength(userInfo.getUsername())
            || !StringUtils.hasLength(userInfo.getPassword())) {
            return AjaxResult.fail(-1 , "非法参数");
        }

        return AjaxResult.success(userService.reg(userInfo));
    }

    @RequestMapping("/login")
    public AjaxResult login(HttpServletRequest req , String username , String password){
        //非空校验
        if(!StringUtils.hasLength(username) || !StringUtils.hasLength(password)){
            return AjaxResult.fail(-1 , "非法请求");
        }
        //查询数据库
        UserInfo userInfo = userService.getUserByName(username);
        if(userInfo != null && userInfo.getId() > 0){
            //判断密码是否相同
            if(password.equals(userInfo.getPassword()) ){
                // 登录成功
                // 将用户存储到 session
                HttpSession session = req.getSession();
                session.setAttribute(AppVariable.USER_SESSION_KEY , userInfo);
                userInfo.setPassword("");// 返回前端之前，隐藏敏感（密码）信息
                return AjaxResult.success(userInfo);
            }
        }
        return AjaxResult.success(0 , null);
    }

    @RequestMapping("/showinfo")
    public AjaxResult showInfo(HttpServletRequest req){
        UserInfoVO userInfoVO = new UserInfoVO();
        // 得到当前登录用户（从 session 中获取）
        UserInfo userInfo = UserSessionUtils.getUser(req);
        if(userInfo == null){
            return AjaxResult.fail(-1 , "非法请求");
        }

        // Spring 提供的深克隆方法
        BeanUtils.copyProperties(userInfo , userInfoVO);
        // 得到用户发表文章的总数
        userInfoVO.setArtCount(articleServie.getArtCountById(userInfo.getId()));
        return AjaxResult.success(userInfoVO);
    }

    @RequestMapping("/getuserbyid")
    public AjaxResult getUserById(Integer id){
        if( id == null || id <= 0){
            return AjaxResult.fail(-1, "非法参数");
        }

        UserInfo userInfo = userService.getUserById(id);
        if(userInfo == null || userInfo.getId() <= 0){
            return AjaxResult.fail(-1 , "非法参数");
        }
        userInfo.setPassword("");
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfo ,userInfoVO);

        userInfoVO.setArtCount(articleServie.getArtCountById(id));
        return AjaxResult.success(userInfoVO);
    }

    @RequestMapping("/logout")
    public AjaxResult logout(HttpSession session){
        session.removeAttribute(AppVariable.USER_SESSION_KEY);
        return AjaxResult.success(1);
    }

}
