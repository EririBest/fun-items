package com.example.service.impl;

import com.example.dao.TakeMapper;
import com.example.domain.TakeRoom;
import com.example.domain.TextObject;
import com.example.service.TakeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/04/25/20:52
 * @brief
 */
@Service
public class TakeServiceImpl implements TakeService {
    @Resource
    private TakeMapper takeMapper;

    @Override
    public void creatTake(Integer userid, String username, String userimg, String room, Integer gid,String gimg) {
        takeMapper.creatTake(userid,username,userimg,room,gid,gimg);
    }

    @Override
    public Boolean inspectTake(Integer userid, Integer gid) {
        TakeRoom takeRoom = takeMapper.inspectTake(userid,gid);
        return takeRoom==null;
    }

    @Override
    public List<TakeRoom> getList(Integer userid) {
        return takeMapper.getList(userid);
    }

    @Override
    public void sendText(String username, String userimg, String ctime, String text, String room) {
        takeMapper.sendText(username,userimg,ctime,text,room);
    }

    @Override
    public List<TextObject> getTextList(String room) {
        return takeMapper.getTextList(room);
    }

    @Override
    public void updateNewText(String username, String room) {
        takeMapper.updateNewText(username,room);
    }

    @Override
    public void readText(Integer uid, String room) {
        takeMapper.readText(uid,room);
    }

    @Override
    public Integer getTotal(Integer userid) {
        return takeMapper.getTotal(userid);
    }

    @Override
    public List<TakeRoom> selectText(String select,Integer userid) {
        return takeMapper.selectText(select,userid);
    }

    @Override
    public void sendImg(String username, String userimg, String ctime, String path,String room) {
        takeMapper.sendImg(username,userimg,ctime,path,room);
    }

    @Override
    public Boolean into(Integer buy, Integer out, String gimg, String ctime, String gname) {
        return takeMapper.into(gname,gimg,ctime,buy,out);
    }
}
