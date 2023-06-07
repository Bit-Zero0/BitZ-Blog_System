package com.example.bitzblogsystem.Controller;

import com.example.bitzblogsystem.Common.AjaxResult;
import com.example.bitzblogsystem.Common.UserSessionUtils;
import com.example.bitzblogsystem.Entity.ArticleInfo;
import com.example.bitzblogsystem.Entity.UserInfo;
import com.example.bitzblogsystem.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/art")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/list")
    public AjaxResult getList(HttpServletRequest req){
        UserInfo userInfo = UserSessionUtils.getUser(req);
        if(userInfo == null){
            return AjaxResult.fail(-1 ,"非法请求");
        }
        List<ArticleInfo> list = articleService.getList(userInfo.getId());
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

        return AjaxResult.success(articleService.delArt(id , userInfo.getId()));
    }

    @RequestMapping("/detail")
    public AjaxResult getArtDetail(Integer id){
        if(id == null && id <= 0){
            AjaxResult.fail(-1 , "非法参数");
        }
        return AjaxResult.success(articleService.getArtDetail(id));
    }

    @RequestMapping("/incr-rcount")
    public AjaxResult incrRCount(Integer id){
        if(id == null || id <= 0){
            AjaxResult.fail(-1 , "非法参数");
        }
        return AjaxResult.success(articleService.incrRCount(id));
    }

    @RequestMapping("/update")
    public AjaxResult updateArt(HttpServletRequest req , ArticleInfo articleInfo){
        if(articleInfo == null || !StringUtils.hasLength(articleInfo.getTitle())
                ||!StringUtils.hasLength(articleInfo.getContent())
                || articleInfo.getId() == null ){
            return AjaxResult.fail(-1 , "非法请求");
        }

        UserInfo userInfo = UserSessionUtils.getUser(req);
        if(userInfo == null || userInfo.getId()== null){
            return AjaxResult.fail(-2 ,"无效用户");
        }

        articleInfo.setUid(userInfo.getId());
        articleInfo.setUpdatetime(LocalDateTime.now());

        return AjaxResult.success(articleService.update(articleInfo));
    }

    @RequestMapping("/add")
    public AjaxResult addArt(HttpServletRequest req ,ArticleInfo articleInfo){
        if(articleInfo == null || !StringUtils.hasLength(articleInfo.getTitle())
            || !StringUtils.hasLength(articleInfo.getContent())){
            return AjaxResult.fail(-1 , "非法请求");
        }

        UserInfo userInfo = UserSessionUtils.getUser(req);
        if(userInfo == null || userInfo.getId() <= 0){
            return AjaxResult.fail(-2 ,"非法用户");
        }

        articleInfo.setCreatetime(LocalDateTime.now());
        articleInfo.setUid(userInfo.getId());

        return AjaxResult.success(articleService.add(articleInfo));
    }

    @RequestMapping("/listbypage")
    public AjaxResult getListByPage(Integer pindex , Integer psize){
        //参数校正
        if(pindex == null || pindex <=  1){
            pindex = 1;
        }
        if(psize == null || psize <= 1){
            psize = 2;
        }

        // 分页公式的值 = (当前页码-1)*每页显示条
        int offset = (pindex - 1) * psize;

        // 文章列表数据
        List<ArticleInfo> list = articleService.getListByPage(psize , offset);

        // 总共有多少条数据
        int totalCount = articleService.getCount();

        // 总条数/psize（每页显示条数）
        double pcountdb = totalCount / ((double)psize);

        // 使用进一法得到总页数
        int pcount = (int) Math.ceil(pcountdb);

        HashMap<String ,Object> result = new HashMap<>();
        result.put("list" , list);
        result.put("pcount" , pcount);
        return AjaxResult.success(result);

    }

}
