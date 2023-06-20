package com.example.dao;

import com.example.domain.CommentObjecct;
import com.example.domain.SonCommentObject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper {
    @Insert("insert into usercomment (`tid`,`uimg`,`uname`,`text`) values (#{id},#{uimg},#{uname},#{text})")
    Boolean send(@Param("id")Integer tid,@Param("uimg") String uimg,@Param("uname") String uname, @Param("text") String text);
    @Select("select * from usercomment where tid=#{id}")
    List<CommentObjecct> getComment(@Param("id") Integer tid);
    @Select("select * from soncomment where cid=#{id}")
    List<SonCommentObject> getSonComment(Integer id);
    @Insert("insert into soncomment (`cid`,`uimg`,`uname`,`rname`,`text`) values (#{cid},#{uimg},#{uname},#{rname},#{text})")
    Boolean sendReply(Integer cid, String uimg, String uname, String rname, String text);
    @Delete("delete from usercomment where id=#{id}")
    Boolean delComment(Integer id);
    @Delete("delete from soncomment where id=#{id}")
    Boolean delSonComment(Integer id);
}
