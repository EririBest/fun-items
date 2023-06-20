package com.example.domain;

import lombok.Data;
import net.sf.jsqlparser.statement.insert.Insert;

/**
 * @author 覃汉宇
 * @date 2023/04/26/13:15
 * @brief
 */
@Data
public class TextObject {
    private Insert id;
    private String username;
    private String userimg;
    private String ctime;
    private String text;
    private String img;
    private String room;
}
