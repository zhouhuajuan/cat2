package com.zhj.event.controller;

import com.zhj.event.service.UserService;
import com.zhj.event.service.impl.UserServiceImpl;

public class UserController {
    /**
     * 实例化一个UserService对象
     */
    UserService userService = new UserServiceImpl();

    /**
     * 以用户的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    public int login(String name,String password){
        return userService.login(name,password);
    }

    /**
     * 注册账号
     * @param name 账号
     * @param password 密码
     * @return 返回一个Boolean结果
     */
    public boolean register(String name, String password) {
        return userService.register(name,password);
    }

    /**
     * 修改密码
     * @param name 账号
     * @param password 密码
     * @param newPassword 新密码
     * @return 返回一个Boolean结果
     */
    public boolean revise(String name, String password, String newPassword) {
        boolean result = false;
        try {
            result = userService.revise(name,password,newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 以管理员的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    public int login1(String name, String password) {
        return userService.login1(name,password);
    }

    /**
     *通过账号获得用户的id
     * @param name 账号
     * @return int
     */
    public int getUserIdByName(String name) {
        return userService.getUserIdByName(name);
    }

    /**
     * 通过用户的id获得余额
     * @param userId 用户id
     * @return boolean
     */
    public boolean getBalanceByUserId(int userId) {
        return userService.getBalanceByUserId(userId);
    }

    /**
     * 充值
     * @param userId 用户id
     * @param balance 余额
     * @return boolean
     */
    public boolean chargeMoney(int userId, int balance) {
        return userService.chargeMoney(userId,balance);
    }

    /**
     * 扣费
     * @param userId 用户id
     * @param balance 余额
     * @return boolean
     */
    public boolean deductMoney(int userId, int balance) {
        return userService.deductMoney(userId,balance);
    }
}
