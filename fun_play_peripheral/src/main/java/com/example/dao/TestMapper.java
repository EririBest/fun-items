package com.example.dao;

import com.example.domain.Goods;
import com.example.domain.TakeRoom;
import com.example.domain.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {
    @Insert("insert into test (`userone`,`usertwo`,`text`,`ctime`) values (#{one},#{two},#{tx},#{ct})")
    Boolean add(@Param("one") Integer user1id,@Param("two") Integer user2id, @Param("tx")String text, @Param("ct")String ctime);
    @Select("select `id`,`userid`,`uimg`,`uname`,`room`,`gid`,`gimg`,`newtext` from takeroom where userid=#{id}")
    List<TakeRoom> get( @Param("id") Integer num2);
}
