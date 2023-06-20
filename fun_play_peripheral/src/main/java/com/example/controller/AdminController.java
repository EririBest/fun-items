package com.example.controller;

import com.example.domain.AdminData;
import com.example.domain.User;
import com.example.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/05/16/19:57
 * @brief 管理员控制层
 */
@CrossOrigin
@RequestMapping("admin")
@RestController
public class AdminController {
    @Resource
    private AdminService adminService;
    @PostMapping("login")
    public Boolean login(String name, String password){
        return adminService.login(name,password) != null;
    }
    @GetMapping("getUser")
    public List<User> getUser(){
        return adminService.getUser();
    }
    @PostMapping("banUser")
    public Boolean banUser(Integer id){
        return adminService.banUser(id);
    }
    @PostMapping("unseal")
    public Boolean unsealUser(Integer id){
        return adminService.unseal(id);
    }
    @PostMapping("rp")
    public Boolean rpassword(Integer id){
        return adminService.rpassword(id);
    }
}
