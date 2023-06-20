package com.example.dao;

import com.example.domain.UsersTake;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface TakeCommunityMapper {
    @Insert("insert into userstake " +
        "(`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`)" +
        "values (#{title},#{text},#{lable},#{img},#{at},#{ati},#{ath},#{ct})")
    Boolean submit(
        @Param("title")String title,
        @Param("text")String text,
        @Param("lable")String lables,
        @Param("img")String imgpath,
        @Param("at")String author,
        @Param("ati")String authorimg,
        @Param("ath")String authorheader,
        @Param("ct")String ctime);
    @Select("select `id`,`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`,`total`,`state` from userstake order by id desc")
    List<UsersTake> adminGet();
    @Update("update userstake set state=#{state} where id=#{id}")
    void pass(@Param("id") Integer id,@Param("state") Integer state);
    @Select("select `id`,`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`,`total`,`state` from userstake where state=#{state} order by id desc LIMIT 0,#{length} ")
    List<UsersTake> userGet(@Param("state") Integer state,@Param("length") Integer length);
    @Select("select `id`,`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`,`total`,`state` from userstake where id=#{id}")
    UsersTake getById(@Param("id") Integer id);
    @Update("update userstake set total=(total+1) where id=#{id}")
    void addTotal(@Param("id")Integer id);
    @Select("select `id`,`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`,`total`,`state` from userstake where lable like concat('%',#{keyword},'%') order by id desc")
    List<UsersTake> selectTake(@Param("keyword") String keyword);
    @Select("select `id`,`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`,`total`,`state` from userstake where author=#{name} order by id desc LIMIT 0,#{length} ")
    List<UsersTake> getMy(@Param("length")Integer length,@Param("name") String name);
    @Select("select `id`,`title`,`text`,`lable`,`imgpath`,`author`,`authorimg`,`authorheader`,`ctime`,`total`,`state` from userstake order by total desc LIMIT 0,#{length} ")
    List<UsersTake> getHot(@Param("length")Integer length);
    @Delete("delete from userstake where id=#{id}")
    Boolean delTake(@Param("id") Integer id);
}
