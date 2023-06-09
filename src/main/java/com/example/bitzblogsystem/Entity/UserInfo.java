package com.example.bitzblogsystem.Entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

// 必须将用户信息进行序列化, 不然无法存入Redis中
@Data
public class UserInfo implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String photo;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private Integer status;
}
