package com.zhj.event.controller;

import com.zhj.event.service.UserService;
import com.zhj.event.service.impl.UserServiceImpl;

public class UserController {
    UserService userService = new UserServiceImpl();
    public boolean login(String name,String password){
        Boolean result  = userService.login(name,password);
        return result;
    }

    public boolean register(String name, String password) {
        Boolean result = userService.register(name,password);
        return result;
    }

    public boolean update(String name, String password, String newpassword) {
        Boolean result = userService.update(name,password,newpassword);
        return result;
    }
}
