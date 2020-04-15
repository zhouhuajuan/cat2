package com.zhj.event.controller;

import com.zhj.event.service.UserService;
import com.zhj.event.service.impl.UserServiceImpl;

public class UserController {
    //实例化一个UserService对象
    UserService userService = new UserServiceImpl();

    /**
     * 以用户的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    public int login(String name,String password){
        int result  = userService.login(name,password);
        return result;
    }

    /**
     * 注册账号
     * @param name 账号
     * @param password 密码
     * @return 返回一个Boolean结果
     */
    public boolean register(String name, String password) {
        Boolean result = userService.register(name,password);
        return result;
    }

    /**
     * 修改密码
     * @param name 账号
     * @param password 密码
     * @param newpassword 新密码
     * @return 返回一个Boolean结果
     */
    public boolean revise(String name, String password, String newpassword) {
        Boolean result = userService.revise(name,password,newpassword);
        return result;
    }

    /**
     * 以管理员的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    public int login1(String name, String password) {
        int result = userService.login1(name,password);
        return result;
    }
}
