package com.example.service.impl;

import com.example.dao.TestMapper;
import com.example.domain.Goods;
import com.example.domain.TakeRoom;
import com.example.domain.Test;
import com.example.service.TestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/16/16:04
 * @brief
 */
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestMapper testMapper;
    @Override
    public Boolean add(Integer user1id, Integer user2id, String text, String ctime) {
        return testMapper.add(user1id,user2id,text,ctime);
    }

    @Override
    public List<TakeRoom> get() {
        return testMapper.get(1);
    }
}
