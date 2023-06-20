package com.example.service;

import com.example.domain.CommentObjecct;
import com.example.domain.SonCommentObject;

import java.util.List;

public interface CommentService {
    Boolean send(Integer tid,String uimg, String uname, String text);

    List<CommentObjecct> getComment(Integer tid);

    List<SonCommentObject> getSonComment(Integer id);

    Boolean sendReply(Integer cid, String uimg, String uname, String rname,String text);

    Boolean delComment(Integer id);

    Boolean delSonComment(Integer id);
}
