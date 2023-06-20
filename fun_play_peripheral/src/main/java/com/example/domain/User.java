package com.example.domain;

import lombok.Data;

/**
 * @author 覃汉宇
 * @date 2023/03/19/16:06
 * @brief 用户实体类
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String oldname;
    private String password;
    private String firpass;
    private String imgpath;
    private String logpath;
    private Integer part;
    private String ipone;
    private String text;
    private Integer flag;
}
