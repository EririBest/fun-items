package com.example.controller;

import com.example.domain.TakeRoom;
import com.example.domain.TextObject;
import com.example.service.TakeService;
import com.example.util.MD5util;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 覃汉宇
 * @date 2023/04/25/20:50
 * @brief
 */
@CrossOrigin
@RequestMapping("take")
@RestController
public class TakeController {
    @Resource
    private TakeService takeService;
    @PostMapping("creat")
    public void creatTake(Integer userid,Integer uid,String username,String uname,String userimg,String uimg,Integer gid,String gimg){
        String room = MD5util.getPWD(username+uname+gid);
        takeService.creatTake(userid,uname,uimg,room,gid,gimg);
        takeService.creatTake(uid,username,userimg,room,gid,gimg);
    }
    @PostMapping("inspect")
    public Boolean inspectTake(Integer userid,Integer gid){
        return takeService.inspectTake(userid,gid);
    }
    @PostMapping("get")
    public List<TakeRoom> getList(Integer userid){
        return takeService.getList(userid);
    }
    @PostMapping("send")
    public void sendText(String room,String username,String userimg,String ctime,String text){
        takeService.updateNewText(username,room);
        takeService.sendText(username,userimg,ctime,text,room);
    }
    @PostMapping("getTextList")
    public List<TextObject> getTextList(String room){
         return takeService.getTextList(room);
    }
    @PostMapping("read")
    public void readText(Integer uid,String room){
        takeService.readText(uid,room);
    }
    @PostMapping("getTotal")
    public Integer getTotal(Integer userid){
        return takeService.getTotal(userid);
    }
    @PostMapping("selectText")
    public List<TakeRoom> selectText(@RequestParam(defaultValue = "")String select,Integer userid){
        return takeService.selectText(select,userid);
    }
    @PostMapping("sendimg")
    public void sendImg(String room,String username,String userimg,String ctime, MultipartFile img){
        String path = "";
        if (img!=null && img.getSize()>0){
            String originalFilename = img.getOriginalFilename();
            String filename =  UUID.randomUUID().toString() + originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                new File("E:\\dates\\userdates\\"+room).mkdirs();
                img.transferTo(new File("E:\\dates\\userdates\\"+room+"\\"+filename));
                path = "base/userdates/"+room+"/"+filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        takeService.updateNewText(username,room);
        takeService.sendImg(username,userimg,ctime,path,room);
    }
    @PostMapping("into")
    public Boolean into(Integer buy,Integer out,String gimg,String gname){
        System.out.println("buy = [" + buy + "], out = [" + out + "], gimg = [" + gimg + "], gname = [" + gname + "]");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String ctime = simpleDateFormat.format(new Date());
        return takeService.into(buy,out,gimg,ctime,gname);
    }
}
