package com.example.bitzblogsystem.Mapper;

import com.example.bitzblogsystem.Common.AjaxResult;
import com.example.bitzblogsystem.Entity.ArticleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int getArtCountByUid(@Param("uid") Integer id);

    List<ArticleInfo> getList(@Param("uid") Integer uid);

    int delArt(@Param("id")Integer id, @Param("uid")Integer uid);

    ArticleInfo getArtDetail(@Param("id") Integer id);

    int incrRCount(@Param("id") Integer id);

    int update(ArticleInfo articleInfo);

    int add(ArticleInfo articleInfo);
}
