package com.example.bitzblogsystem.Service;

import com.example.bitzblogsystem.Entity.ArticleInfo;
import com.example.bitzblogsystem.Mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public int getArtCountById(Integer id){
        return articleMapper.getArtCountByUid(id);
    }

    public List<ArticleInfo> getList(Integer uid){
        return articleMapper.getList(uid);
    }

    public int delArt(Integer id, Integer uid){
        return articleMapper.delArt(id, uid);
    }

    public ArticleInfo getArtDetail(Integer id){
        return articleMapper.getArtDetail(id);
    }

    public int incrRCount(Integer id){
        return articleMapper.incrRCount(id);
    }

    public int update(ArticleInfo articleInfo){
        return articleMapper.update(articleInfo);
    }

    public int add(ArticleInfo articleInfo){
        return articleMapper.add(articleInfo);
    }

    public List<ArticleInfo> getListByPage(Integer psize , Integer offsize){
        return articleMapper.getListByPage(psize , offsize);
    };

    public int getCount(){
        return articleMapper.getCount();
    }
}
