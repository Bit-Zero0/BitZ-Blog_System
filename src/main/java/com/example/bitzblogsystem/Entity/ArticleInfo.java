package com.example.bitzblogsystem.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ArticleInfo {
    private Integer id;
    private String title;
    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private LocalDateTime createtime;

    @JsonFormat(pattern = "yyyy-MM-dd" , timezone = "GMT+8")
    private LocalDateTime updatetime;

    private Integer uid;
    private Integer rcount;
    private Integer state;
}
