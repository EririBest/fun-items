package com.example.service;


import com.example.domain.Goods;

import java.util.List;

public interface CommodityService {

    void addUserGoods(Integer id, String title,Integer price,String type,String firstImgPath, String xmlPath);

    List<Goods> getUserCommoditys(Integer userId);

    List<Goods> getCommoditys();

    Goods getById(Integer id);

    List<Goods> getKey();

    List<Goods> getByType(String keyword);

    List<Goods> select(String keyword);

    String[] getTypes(String name);

    Boolean delete(Integer id);
}
