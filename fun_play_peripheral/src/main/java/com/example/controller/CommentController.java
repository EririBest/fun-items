package com.example.controller;

import com.example.domain.CommentObjecct;
import com.example.service.CommentService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/05/06/12:44
 * @brief
 */
@CrossOrigin
@RequestMapping("com")
@RestController
public class CommentController {
    @Resource
    private CommentService commentService;
    @RequestMapping("sendComment")
    public Boolean send(Integer tid,String uimg,String uname,String text){
        return commentService.send(tid,uimg,uname,text);
    }
    @RequestMapping("sendReply")
    public Boolean sengReply(Integer cid,String uimg,String uname,String rname,String text){
        System.out.println("cid = [" + cid + "], text = [" + text + "], uname = [" + uname + "], rname = [" + rname + "], uimg = [" + uimg + "]");
        return commentService.sendReply(cid,uimg,uname,rname,text);
    }
    @RequestMapping("getComment")
    public List<CommentObjecct> getComment(Integer tid){
        List<CommentObjecct> arr = commentService.getComment(tid);
        for (int i = 0; i < arr.size(); i++) {
            arr.get(i).setSonCommentObjectList(commentService.getSonComment(arr.get(i).getId()));
        }
        return arr;
    }
    @PostMapping("del")
    public Boolean delComment(Integer id){
        return commentService.delComment(id);
    }
    @PostMapping("delSon")
    public Boolean delSonComment(Integer id){
        return commentService.delSonComment(id);
    }
}
