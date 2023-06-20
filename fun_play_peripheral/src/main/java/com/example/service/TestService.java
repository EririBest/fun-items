package com.example.service;

import com.example.domain.Goods;
import com.example.domain.TakeRoom;
import com.example.domain.Test;

import java.util.List;

public interface TestService {
    Boolean add(Integer user1id,Integer user2id, String text, String ctime);

    List<TakeRoom> get();
}
