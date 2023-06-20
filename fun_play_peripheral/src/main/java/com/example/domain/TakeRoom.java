package com.example.domain;

import lombok.Data;

import java.util.List;


/**
 * @author 覃汉宇
 * @date 2023/04/25/21:42
 * @brief
 */
@Data
public class TakeRoom {
    private Integer id;
    private Integer userid;
    private String uimg;
    private String uname;
    private String room;
    private Integer gid;
    private String gimg;
    private Integer newtext;
    private List<TextObject> arr;
}
