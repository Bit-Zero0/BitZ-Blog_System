package com.example.bitzblogsystem.Service;

import com.example.bitzblogsystem.Entity.UserInfo;
import com.example.bitzblogsystem.Mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int reg(UserInfo userInfo){
        return userMapper.reg(userInfo);
    }

    public UserInfo getUserByName(String username){
        return userMapper.getUserByName(username);
    }

    public UserInfo getUserById(Integer id){
        return userMapper.getUserById(id);
    }
}
