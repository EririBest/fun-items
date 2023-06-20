package com.example.service;

import com.example.domain.UsersTake;

import java.util.List;

public interface TakeCommunityService {
    Boolean submit(UsersTake usersTake, String imgpath, String lables);

    List<UsersTake> adminGet();

    void pass(Integer id,Integer state);

    List<UsersTake> userGet(Integer state, Integer length);

    UsersTake getById(Integer id);

    void addTotal(Integer id);

    List<UsersTake> selectTake(String keyword);

    List<UsersTake> getMy(Integer length, String name);

    List<UsersTake> getHot(Integer length);

    Boolean delTake(Integer id);
}
