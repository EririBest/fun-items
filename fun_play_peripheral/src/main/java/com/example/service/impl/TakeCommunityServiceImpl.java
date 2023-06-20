package com.example.service.impl;

import com.example.dao.TakeCommunityMapper;
import com.example.domain.UsersTake;
import com.example.service.TakeCommunityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/05/04/14:51
 * @brief
 */
@Service
public class TakeCommunityServiceImpl implements TakeCommunityService {
    @Resource
    private TakeCommunityMapper takeCommunityMapper;

    @Override
    public Boolean submit(UsersTake usersTake, String imgpath, String lables) {
        return takeCommunityMapper.submit(
            usersTake.getTitle(),usersTake.getText(),lables,imgpath,
            usersTake.getAuthor(),usersTake.getAuthorimg(),usersTake.getAuthorheader(),usersTake.getCtime()
        );
    }

    @Override
    public List<UsersTake> adminGet() {
        return takeCommunityMapper.adminGet();
    }

    @Override
    public void pass(Integer id,Integer state) {
        takeCommunityMapper.pass(id, state);
    }

    @Override
    public List<UsersTake> userGet(Integer state, Integer length) {
        return takeCommunityMapper.userGet(state,length);
    }

    @Override
    public UsersTake getById(Integer id) {
        return takeCommunityMapper.getById(id);
    }

    @Override
    public void addTotal(Integer id) {
        takeCommunityMapper.addTotal(id);
    }

    @Override
    public List<UsersTake> selectTake(String keyword) {
        return takeCommunityMapper.selectTake(keyword);
    }

    @Override
    public List<UsersTake> getMy(Integer length, String name) {
        return takeCommunityMapper.getMy(length,name);
    }

    @Override
    public List<UsersTake> getHot(Integer length) {
        return takeCommunityMapper.getHot(length);
    }

    @Override
    public Boolean delTake(Integer id) {
        return takeCommunityMapper.delTake(id);
    }
}
