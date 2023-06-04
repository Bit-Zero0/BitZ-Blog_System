package com.example.bitzblogsystem.Mapper;

import com.example.bitzblogsystem.Entity.ArticleInfo;
import com.example.bitzblogsystem.Entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    int reg(UserInfo userInfo);

    UserInfo getUserByName(@Param("username")String usename);

    UserInfo getUserById(@Param("id") Integer id);

}

