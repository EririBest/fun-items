package com.example.controller;

import com.example.domain.TakeRoom;
import com.example.service.TakeService;
import com.example.service.TestService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/03/19/15:51
 * @brief 测试模块
 * 用于测试新的功能
 */
@RestController
@CrossOrigin
@RequestMapping("test")
public class TestController {
    @Resource
    private TestService testService;
    @Resource
    private TakeService takeService;
    @RequestMapping("tekaAdd")
    public Boolean add(
        @Param("text")String text,
        @Param("ctime") String ctime){
        return testService.add(1,2,text,ctime);
    }
    @RequestMapping("get")
    public List<TakeRoom> get(){
        List<TakeRoom> takeRooms = testService.get();
        for (int i = 0; i < takeRooms.size(); i++) {
            takeRooms.get(i).setArr(takeService.getTextList(takeRooms.get(i).getRoom()));
        }
        return takeRooms;
    }
}
