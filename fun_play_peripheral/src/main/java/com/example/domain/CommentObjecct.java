package com.example.domain;

import lombok.Data;

import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/05/06/12:41
 * @brief
 */
@Data
public class CommentObjecct {
    private Integer id;
    private String uimg;
    private String uname;
    private String text;
    private String img;
    private Integer total;
    private List<SonCommentObject> sonCommentObjectList;
    private Integer reply;
    private Integer show;
}
