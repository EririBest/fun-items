package com.example.service.impl;

import com.example.dao.CommodityMapper;
import com.example.domain.Goods;
import com.example.service.CommodityService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/18/16:37
 * @brief
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    @Resource
    private CommodityMapper commodityMapper;

    @Override
    public void addUserGoods(Integer id, String title,Integer price,String type, String firstImgPath, String xmlPath) {
        commodityMapper.addUserGoods(id,title,price,type,firstImgPath,xmlPath);
    }

    @Override
    public List<Goods> getUserCommoditys(Integer userId) {
        return commodityMapper.getUserCommoditys(userId);
    }

    @Override
    public List<Goods> getCommoditys() {
        return commodityMapper.getCommoditys();
    }

    @Override
    public Goods getById(Integer id) {
        return commodityMapper.getById(id);
    }

    @Override
    public List<Goods> getKey() {
        return commodityMapper.getKey();
    }

    @Override
    public List<Goods> getByType(String keyword) {
        return commodityMapper.getByType(keyword);
    }

    @Override
    public List<Goods> select(String keyword) {
        return commodityMapper.select(keyword);
    }

    @Override
    public String[] getTypes(String name) {
        return commodityMapper.getTypes(name);
    }

    @Override
    public Boolean delete(Integer id) {
        return commodityMapper.delete(id);
    }
}
