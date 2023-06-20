package com.example.service.impl;

import com.example.dao.UserMapper;
import com.example.domain.BuyOrOut;
import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/03/19/16:05
 * @brief
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public User useLogin(String name, String password) {
        return userMapper.useLogin(name,password);
    }

    @Override
    public User getUsername(String name) {
        return userMapper.getUserName(name);
    }

    @Override
    public Boolean useRegister(String name, String password) {
        return userMapper.useRegister(name,password);
    }

    @Override
    public Boolean upload(Integer id, String path) {
        return userMapper.upload(id,path);
    }

    @Override
    public Integer getPart(Integer id) {
        return userMapper.getPart(id);
    }

    @Override
    public Boolean upPart(Integer id, Integer part) {
        return userMapper.upPart(id,part);
    }

    @Override
    public Boolean addData(User user) {
        if(user.getText().equals("")){
            user.setText("这个人很神秘,没有简介");
        }
        return userMapper.addData(user.getId(),user.getText(),user.getIpone());
    }

    @Override
    public List<User> selectUser(String name) {
        return userMapper.selectUser(name);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void updataBgImg(String imgpath, Integer id) {
        userMapper.updateBgImg(imgpath,id);
    }

    @Override
    public void upTake(String name, String path) {
        userMapper.upTakeRoom(name,path);
        userMapper.upTake(name,path);
    }

    @Override
    public void upComment(String name, String path) {
        userMapper.upComment(name,path);
        userMapper.upSonComment(name,path);
    }

    @Override
    public void updateCommodity(String name, String path) {
        userMapper.upCommodity(name,path);
    }

    @Override
    public void updateTakeBg(String name, String path) {
        userMapper.updateTakeBg(name,path);
    }

    @Override
    public Boolean update(User user) {
        return userMapper.update(user.getId(),user.getName(),user.getPassword(),user.getText());
    }

    @Override
    public void upRoomName(String oldname, String name) {
        userMapper.upRoomName(oldname,name);
    }

    @Override
    public void upTakeName(String oldname, String name) {
        userMapper.upTakeName(oldname,name);
    }

    @Override
    public void upHta(String oldname, String name) {
        userMapper.upHta(oldname,name);
    }

    @Override
    public void upCommentName(String oldname, String name) {
        userMapper.upCommentName(oldname,name);
    }

    @Override
    public void upSonCommentName(String oldname, String name) {
        userMapper.upSonCommentName(oldname,name);
        userMapper.upSonCommentR(oldname, name);
    }

    @Override
    public List<BuyOrOut> getOut(Integer id) {
        return userMapper.getOut(id);
    }

    @Override
    public List<BuyOrOut> getBut(Integer id) {
        return userMapper.getBuy(id);
    }
}
