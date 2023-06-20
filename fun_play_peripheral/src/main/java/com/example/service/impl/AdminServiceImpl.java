package com.example.service.impl;

import com.example.dao.AdminMapper;
import com.example.domain.AdminData;
import com.example.domain.User;
import com.example.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 覃汉宇
 * @date 2023/05/16/20:07
 * @brief
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public AdminData login(String name, String password) {
        return adminMapper.login(name,password);
    }

    @Override
    public List<User> getUser() {
        return adminMapper.getUser();
    }

    @Override
    public Boolean banUser(Integer id) {
        return adminMapper.banUser(id);
    }

    @Override
    public Boolean unseal(Integer id) {
        return adminMapper.unsealUser(id);
    }

    @Override
    public Boolean rpassword(Integer id) {
        return adminMapper.rpassword(id);
    }
}
