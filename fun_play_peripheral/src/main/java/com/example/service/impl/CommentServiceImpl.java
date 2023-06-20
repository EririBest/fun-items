package com.example.service.impl;

import com.example.dao.CommentMapper;
import com.example.domain.CommentObjecct;
import com.example.domain.SonCommentObject;
import com.example.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/05/06/12:45
 * @brief
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    private CommentMapper commentMapper;

    @Override
    public Boolean send(Integer tid,String uimg, String uname, String text) {
        return commentMapper.send(tid,uimg,uname,text);
    }

    @Override
    public List<CommentObjecct> getComment(Integer tid) {
        return commentMapper.getComment(tid);
    }

    @Override
    public List<SonCommentObject> getSonComment(Integer id) {
        return commentMapper.getSonComment(id);
    }

    @Override
    public Boolean sendReply(Integer cid, String uimg, String uname, String rname,String text) {
        return commentMapper.sendReply(cid,uimg,uname,rname,text);
    }

    @Override
    public Boolean delComment(Integer id) {
        return commentMapper.delComment(id);
    }

    @Override
    public Boolean delSonComment(Integer id) {
        return  commentMapper.delSonComment(id);
    }
}
