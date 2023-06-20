package com.example.dao;

import com.example.domain.AdminData;
import com.example.domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdminMapper {
    @Select("select `id`,`name`,`password` from admins where name=#{name} and password=#{password}")
    AdminData login(String name, String password);

    @Select("select * from users")
    List<User> getUser();

    @Update("update users set flag=0 where id=#{id}")
    Boolean banUser(Integer id);

    @Update("update users set flag=1 where id=#{id}")
    Boolean unsealUser(Integer id);
    @Update("update users set password=firpass where id=#{id}")
    Boolean rpassword(Integer id);
}
