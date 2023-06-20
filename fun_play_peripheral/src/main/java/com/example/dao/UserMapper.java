package com.example.dao;

import com.example.domain.BuyOrOut;
import com.example.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface UserMapper {

    User useLogin(@Param("name") String name, @Param("password")String password);

    User getUserName(@Param("name")String name);

    Boolean useRegister(@Param("name")String name, @Param("password")String password);

    Boolean upload(@Param("id")Integer id, @Param("path")String path);

    Integer getPart(@Param("id")Integer id);

    Boolean upPart(@Param("id")Integer id,@Param("part") Integer part);

    Boolean addData(@Param("id")Integer id,@Param("text")String text,@Param("ipone") String ipone);

    List<User> selectUser(@Param("name") String name);

    User getById(@Param("id")Integer id);
    @Update("update users set logpath=#{path} where id=#{id}")
    void updateBgImg(@Param("path") String imgpath,@Param("id") Integer id);
    @Update("update takeroom set uimg=#{img} where uname=#{name}")
    void upTakeRoom(@Param("name") String name,@Param("img") String path);
    @Update("update taketext set userimg=#{img} where username=#{name}")
    void upTake(@Param("name")String name, @Param("img")String path);
    @Update("update usercomment set uimg=#{path} where uname=#{name}")
    void upComment(@Param("name") String name,@Param("path") String path);
    @Update("update soncomment set uimg=#{path} where uname=#{name}")
    void upSonComment(@Param("name") String name,@Param("path") String path);
    @Update("update userstake set authorheader=#{path} where author=#{name}")
    void upCommodity(@Param("name") String name, @Param("path")String path);
    @Update("update userstake set authorimg=#{path} where author=#{name}")
    void updateTakeBg(@Param("name") String name, @Param("path")String path);
    @Update("update users set name=#{name},text=#{text},password=#{password} where id=#{id}")
    Boolean update(Integer id, String name, String password, String text);

    @Update("update takeroom set uname=#{name} where uname=#{oname}")
    void upRoomName(@Param("oname")String oldname,@Param("name") String name);
    @Update("update taketext set username=#{name} where username=#{oname}")
    void upTakeName(@Param("oname")String oldname,@Param("name") String name);
    @Update("update userstake set  author=#{name} where author=#{oname}")
    void upHta(@Param("oname") String oldname, @Param("name") String name);
    @Update("update usercomment set uname=#{name} where uname=#{oname} ")
    void upCommentName(@Param("oname")String oldname,@Param("name") String name);
    @Update("update soncomment set uname=#{name} where uname=#{oname}")
    void upSonCommentName(@Param("oname")String oldname,@Param("name") String name);
    @Update("update soncomment set rname=#{name} where rname=#{oname}")
    void upSonCommentR(@Param("oname")String oldname,@Param("name") String name);
    @Select("select * from jiaoye where `out`=#{id} order by id desc")
    List<BuyOrOut> getOut(Integer id);
    @Select("select * from jiaoye where buy=#{id} order by id desc")
    List<BuyOrOut> getBuy(Integer id);
}
