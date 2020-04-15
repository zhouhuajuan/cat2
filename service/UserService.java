package com.zhj.event.service;

public interface UserService {

    /**
     * 注册账号
     * @param name 账号
     * @param password 密码
     * @return 返回一个boolean结果
     */
    boolean register(String name, String password);

    /**
     * 以用户的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    int login(String name, String password);

    /**
     * 修改密码
     * @param name 账号
     * @param password 密码
     * @param newpassword 新密码
     * @return 返回一个boolean结果
     */
    boolean revise(String name, String password, String newpassword);

    /**
     * 以管理员的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    int login1(String name, String password);
}
