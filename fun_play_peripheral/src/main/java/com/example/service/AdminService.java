package com.example.service;

import com.example.domain.AdminData;
import com.example.domain.User;

import java.util.List;

public interface AdminService {
    AdminData login(String name, String password);

    List<User> getUser();

    Boolean banUser(Integer id);

    Boolean unseal(Integer id);

    Boolean rpassword(Integer id);
}
