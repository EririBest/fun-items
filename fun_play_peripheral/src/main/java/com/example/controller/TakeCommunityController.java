package com.example.controller;

import com.example.domain.UsersTake;
import com.example.service.TakeCommunityService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * @author 覃汉宇
 * @date 2023/05/04/14:47
 * @brief
 */
@CrossOrigin
@RequestMapping("community")
@RestController
public class TakeCommunityController {
    @Resource
    private TakeCommunityService takeCommunityService;
    @RequestMapping("submit")
    public Boolean submit(UsersTake usersTake, MultipartFile[] files,String ldd){
        int i = 0;
        String imgpath = "";
        String md = UUID.randomUUID().toString();
        for (MultipartFile photo:files){
            if (photo!=null && photo.getSize()>0){
                i++;
                String originalFilename = photo.getOriginalFilename();
                String filename = i + originalFilename.substring(originalFilename.lastIndexOf("."));
                imgpath += "base/userdates/"+usersTake.getAuthor()+"/"+md+"/"  + filename + "&";
                try {
                    new File("E:\\dates\\userdates\\"+usersTake.getAuthor()+"\\"+ md).mkdirs();
                    photo.transferTo(new File("E:\\dates\\userdates\\"+usersTake.getAuthor()+"\\"+md+"\\"+filename));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return takeCommunityService.submit(usersTake,imgpath,ldd);
    }
    @GetMapping("adminGet")
    public List<UsersTake> adminGet (){
        return takeCommunityService.adminGet();
    }
    @PostMapping("pass")
    public void pass(Integer id,Integer state){
        takeCommunityService.pass(id,state);
    }
    @PostMapping("userGet")
    public List<UsersTake> userGet (Integer state,Integer length){
        return takeCommunityService.userGet(state,length);
    }
    @PostMapping("getById")
    public UsersTake getById(Integer id){
        return takeCommunityService.getById(id);
    }
    @PostMapping("addTotal")
    public void addTotal(Integer id){
        takeCommunityService.addTotal(id);
    }
    @PostMapping("lable")
    public List<UsersTake> selectlable (String keyword){
        return takeCommunityService.selectTake(keyword);
    }
    @PostMapping("getMy")
    public List<UsersTake> getMy(Integer length,String name){
        return takeCommunityService.getMy(length,name);
    }
    @PostMapping("getHot")
    public List<UsersTake> getHot(Integer length){
        return takeCommunityService.getHot(length);
    }
    @PostMapping("del")
    public Boolean delTake (Integer id) {
        return takeCommunityService.delTake(id);
    }
}
