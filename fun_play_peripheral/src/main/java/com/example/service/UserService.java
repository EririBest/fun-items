package com.example.service;

import com.example.domain.BuyOrOut;
import com.example.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    User useLogin(String name, String password);

    User getUsername(String name);

    Boolean useRegister(String name, String password);

    Boolean upload(Integer id,String path);

    Integer getPart(Integer id);

    Boolean upPart(Integer id, Integer part);

    Boolean addData(User user);

    List<User> selectUser(String name);

    User getById(Integer id);

    void updataBgImg(String imgpath, Integer id);

    void upTake(String name, String path);

    void upComment(String name, String path);

    void updateCommodity(String name, String path);

    void updateTakeBg(String name, String path);

    Boolean update(User user);

    void upRoomName(String oldname,String name);

    void upTakeName(String oldname,String name);

    void upHta(String oldname,String name);

    void upCommentName(String oldname,String name);

    void upSonCommentName(String oldname,String name);

    List<BuyOrOut> getOut(Integer id);

    List<BuyOrOut> getBut(Integer id);
}
