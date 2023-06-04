package com.example.bitzblogsystem.Service;

import com.example.bitzblogsystem.Entity.ArticleInfo;
import com.example.bitzblogsystem.Mapper.ArticleMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServie {

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
}
