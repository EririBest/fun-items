package com.example.dao;

import com.example.domain.TakeRoom;
import com.example.domain.TextObject;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeMapper {
    @Insert("insert into takeroom (`userid`,`uimg`,`uname`,`room`,`gid`,`gimg`) values (#{id},#{img},#{name},#{room},#{gid},#{gimg})")
    void creatTake(@Param("id")Integer userid,@Param("name") String username,@Param("img") String userimg,@Param("room") String room,@Param("gid") Integer gid,@Param("gimg")String gimg);

    TakeRoom inspectTake(@Param("id") Integer userid,@Param("gid") Integer gid);

    List<TakeRoom> getList(@Param("id")Integer userid);

    void sendText(@Param("uname") String username,@Param("uimg") String userimg, @Param("ctime") String ctime,@Param("text") String text,@Param("room") String room);

    List<TextObject> getTextList(@Param("room") String room);
    @Update("update  takeroom set newtext=(newtext+1) where uname=#{uname} and room=#{room}")
    void updateNewText(@Param("uname") String username,@Param("room") String room);
    @Update("update  takeroom set newtext=0 where userid=#{uid} and room=#{room}")
    void readText(@Param("uid")Integer uid, @Param("room")String room);
    @Select("SELECT sum(newtext) FROM `takeroom` where userid=#{id}")
    Integer getTotal(@Param("id") Integer userid);

    List<TakeRoom> selectText(@Param("select") String select,@Param("id")Integer userid);

    void sendImg(@Param("uname") String username,@Param("uimg") String userimg, @Param("ctime") String ctime,@Param("img") String path,@Param("room") String room);
    @Insert("insert into jiaoye (`gname`,`gimg`,`ctime`,`buy`,`out`) values (#{gname},#{gimg},#{ctime},#{buy},#{out})")
    Boolean into(String gname, String gimg, String ctime, Integer buy, Integer out);
}
