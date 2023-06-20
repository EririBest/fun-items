package com.example.domain;

import lombok.Data;

/**
 * @author 覃汉宇
 * @date 2023/04/18/10:42
 * @brief
 */
@Data
public class Goods {
    private Integer id;
    private Integer userId;
    private String title;
    private Integer price;
    private String type;
    private String imgpath;
    private String xmlpath;
}
