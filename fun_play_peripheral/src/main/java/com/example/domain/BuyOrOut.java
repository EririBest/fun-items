package com.example.domain;

import lombok.Data;

/**
 * @author 覃汉宇
 * @date 2023/05/17/11:54
 * @brief
 */
@Data
public class BuyOrOut {
    private Integer id;
    private String gname;
    private String gimg;
    private String ctime;
    private Integer buy;
    private Integer out;
}
