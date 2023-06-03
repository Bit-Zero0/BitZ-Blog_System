package com.example.bitzblogsystem.Entity.VO;

import com.example.bitzblogsystem.Entity.UserInfo;
import lombok.Data;

@Data
public class UserInfoVO extends UserInfo {
    private Integer artCount; //此人的发表的文章总数
}
