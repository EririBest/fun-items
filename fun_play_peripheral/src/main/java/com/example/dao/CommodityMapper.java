package com.example.dao;


import com.example.domain.Goods;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/18/16:37
 * @brief
 */
@Repository
public interface CommodityMapper {
    void addUserGoods(@Param("id")Integer id,@Param("title") String title,@Param("price")Integer price,@Param("type") String type,@Param("ipath") String firstImgPath, @Param("xpath")String xmlPath);

    List<Goods> getUserCommoditys(@Param("id")Integer userId);

    List<Goods> getCommoditys();

    Goods getById(@Param("id")Integer id);

    List<Goods> getKey();

    List<Goods> getByType(@Param("keyword") String keyword);

    List<Goods> select(@Param("keyword") String keyword);
    @Select("select distinct `type` from usergoods where title like concat(#{name},'%') or type like concat(#{name},'%') LIMIT 0,6")
    String[] getTypes(@Param("name") String name);
    @Delete("delete from usergoods where id=#{id}")
    Boolean delete(Integer id);
}
