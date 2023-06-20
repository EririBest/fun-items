package com.example.controller;

import com.example.domain.BuyOrOut;
import com.example.domain.User;
import com.example.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/03/16/10:10
 * @brief 用户控制层
 * 包含方法: 注册 登录 查重用户名
 */
@SuppressWarnings("all")
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {
    @Resource
    private UserService userService;
    /*
     * 17:40
     * 用于验证用户的登录信息
    */
    @PostMapping("login")
    public User useLogin(
        @RequestParam("name") String name,
        @RequestParam("password")String password){
        return userService.useLogin(name,password);
    }
    /*
     * 17:41
     * 用于获取用户是否重名
    */
    @PostMapping("getname")
    public Boolean getUserName(
        @Param("name") String name
    ){
        System.out.println("name = " + name);
        return userService.getUsername(name) == null;
    }
    @PostMapping("byname")
    public User getUserByName(
        @Param("name") String name
    ){
        return userService.getUsername(name);
    }
    /*
     * 17:41
     * 用于用户注册
    */
    @PostMapping("register")
    public Boolean useRegister(
        @RequestParam("name") String name,
        @RequestParam("password")String password){
        return userService.useRegister(name,password);
    }
    /*
     * 14:47
     * 用户上传头像
    */
    @PostMapping("/up")
    public String update(User user,MultipartFile photo){
        System.out.println(user);
        String path = "";
        if (photo!=null && photo.getSize()>0){
            String originalFilename = photo.getOriginalFilename();
            String filename = user.getName() + "HeadSculpture"+ new Date().getTime() + originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                new File("E:\\dates\\userdates\\"+user.getName()+user.getId()).mkdirs();
                photo.transferTo(new File("E:\\dates\\userdates\\"+user.getName()+user.getId()+"\\"+filename));
                path = "base/userdates/"+user.getName()+user.getId()+"/"+filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userService.upload(user.getId(),path);
        userService.upTake(user.getName(),path);
        userService.upComment(user.getName(),path);
        userService.updateCommodity(user.getName(),path);
        return path;
    }
    @PostMapping("/part")
    public Integer getPart(@RequestParam("id")Integer id){
        return  userService.getPart(id);
    }

    @PostMapping("/uppart")
    public Boolean upPart(@RequestParam("id")Integer id,@RequestParam("part") Integer part){
        return  userService.upPart(id,part);
    }

    @PostMapping("/adddata")
    public Boolean addData(User user){
        return userService.addData(user);
    }
    @PostMapping("test")
    public void test(MultipartFile img){
        System.out.println(img.getSize());
//        int i = 0;
//        for (MultipartFile photo:files
//             ) {
//            if (photo!=null && photo.getSize()>0){
//                i++;
//                String originalFilename = photo.getOriginalFilename();
//                String filename = i + "HeadSculpture" + originalFilename.substring(originalFilename.lastIndexOf("."));
//                try {
//                    new File("E:\\dates\\userdates\\test").mkdirs();
//                    photo.transferTo(new File("E:\\dates\\userdates\\test\\"+filename));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
    @RequestMapping("select")
    public List<User> selectUser(String name){
        return userService.selectUser(name);
    }
    @PostMapping("getById")
    public User getById(Integer id){
        return userService.getById(id);
    }
    @PostMapping("updateBgImg")
    public User updateBgImg(String name,Integer id,MultipartFile photo){
        String path = "";
        if (photo!=null && photo.getSize()>0){
            String originalFilename = photo.getOriginalFilename();
            String filename = name + "BgPhoto"+ new Date().getTime() + originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                new File("E:\\dates\\userdates\\"+name+id).mkdirs();
                photo.transferTo(new File("E:\\dates\\userdates\\"+name+id+"\\"+filename));
                path = "base/userdates/"+name+id+"/"+filename;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userService.updateTakeBg(name,path);
        userService.updataBgImg(path,id);
        return  userService.getUsername(name);
    }
    @PostMapping("update")
    public Boolean update(User user){
        System.out.println(user);
        userService.upCommentName(user.getOldname(),user.getName());
        userService.upSonCommentName(user.getOldname(),user.getName());
        userService.upHta(user.getOldname(),user.getName());
        userService.upRoomName(user.getOldname(),user.getName());
        userService.upTakeName(user.getOldname(),user.getName());
        return userService.update(user);
    }
    @PostMapping("ckps")
    public Boolean ckps(String name,String ps){
        System.out.println("name = [" + name + "], ps = [" + ps + "]");
        return !ps.equals(userService.getUsername(name).getFirpass());
    }
    @PostMapping("out")
    public List<BuyOrOut> getOut(Integer id){
        return userService.getOut(id);
    }
    @PostMapping("buy")
    public List<BuyOrOut> getBut(Integer id){
        return userService.getBut(id);
    }
}
