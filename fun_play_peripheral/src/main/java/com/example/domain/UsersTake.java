package com.example.domain;

import lombok.Data;

/**
 * @author 覃汉宇
 * @date 2023/05/04/14:30
 * @brief
 */
@Data
public class UsersTake {
    private Integer id;
    private String title;
    private String text;
    private String lable;
    private String imgpath;
    private String author;
    private String authorimg;
    private String authorheader;
    private String ctime;
    private Integer total;
    private Integer state;
}
