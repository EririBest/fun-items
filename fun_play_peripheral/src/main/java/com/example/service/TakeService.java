package com.example.service;

import com.example.domain.TakeRoom;
import com.example.domain.TextObject;
import net.sf.jsqlparser.statement.insert.Insert;

import java.util.List;

public interface TakeService {
    void creatTake(Integer userid, String username, String userimg, String room, Integer gid, String gimg);

    Boolean inspectTake(Integer userid, Integer gid);

    List<TakeRoom> getList(Integer userid);


    void sendText(String username, String userimg, String ctime, String text, String room);

    List<TextObject> getTextList(String room);

    void updateNewText(String username,String room);

    void readText(Integer uid, String room);

    Integer getTotal(Integer userid);

    List<TakeRoom> selectText(String select,Integer userid);

    void sendImg(String username, String userimg, String ctime, String path,String room);

    Boolean into(Integer buy, Integer out, String gimg, String ctime, String gname);
}
