package com.example.bitzblogsystem.Controller;

import com.example.bitzblogsystem.Common.AjaxResult;
import com.example.bitzblogsystem.Common.UserSessionUtils;
import com.example.bitzblogsystem.Entity.ArticleInfo;
import com.example.bitzblogsystem.Entity.UserInfo;
import com.example.bitzblogsystem.Service.ArticleServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArticleController {
    @Autowired
    private ArticleServie articleServie;

    @RequestMapping("/list")
    public AjaxResult getList(HttpServletRequest req){
        UserInfo userInfo = UserSessionUtils.getUser(req);
        if(userInfo == null){
            return AjaxResult.fail(-1 ,"非法请求");
        }
        List<ArticleInfo> list = articleServie.getList(userInfo.getId());
        return AjaxResult.success(list);
    }

    @RequestMapping("/delArt")
    public AjaxResult delArt(HttpServletRequest req , Integer id){
        if(id == null || id <= 0){
            return AjaxResult.fail(-1 , "非法参数");
        }

        UserInfo userInfo = UserSessionUtils.getUser(req);
        if(userInfo == null){
            return AjaxResult.fail(-2 , "用户未登录");
        }

        return AjaxResult.success(articleServie.delArt(id , userInfo.getId()));
    }
}
