package com.example.controller;

import com.example.util.QRCodeUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Random;

/**
 * @date 2023/02/23/11:03
 * @brief 用户注册二维码验证生成控制层和密码的生成
 */
@CrossOrigin
@RestController
@RequestMapping("cod")
public class QcodController {
    private String password ;
    /*
     * 17:42
     * 生成获取密码的二维码
    */
    @RequestMapping("img")
    public void getpassword(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        password = "";
        Random rand = new Random();
        String [] wordtype = {"A","a","B","b","C","c","D","d","@","$","%","F","G","H","J","j","r","i","g",
            "L","K","k","y","u","o","1","2","3","4","5","6","7","8","9","0","Q","b","x","0","9","8","2","3","6","5"};
        for (int i = 0; i < 9; i++) {
            password += wordtype[rand.nextInt(wordtype.length-1)];
        }
        OutputStream outputStream = resp.getOutputStream();
        QRCodeUtils.encode("请务必牢记您的初始密码，如果忘记了密码需要使用初始密码来重置账号密码," +
            "初始密码为:"+password,outputStream);
    }
    @RequestMapping("zfbPay")
    public void zfbPay(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        OutputStream outputStream = resp.getOutputStream();
        QRCodeUtils.encode("https://auth.alipay.com/login/index.htm?goto=https%3A%2F%2F110.alipay.com%2Fsc%2Findex.htm",outputStream);
    }
    /*
     * 17:43
     * 获取密码
    */
    @RequestMapping("password")
    public String getPassword(){
        return password;
    }
}
