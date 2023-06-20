package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/22/15:25
 * @brief
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HtmlCommodityDataObject {
    private Goods goods;
    private String text;
    private List<String> imgs;
}
