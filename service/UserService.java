package com.zhj.event.service;

public interface UserService {

    /**
     * 注册账号
     * @param name
     * @param password
     * @return
     */
    boolean register(String name, String password);

    /**
     * 以用户的身份登陆系统
     * @param name
     * @param password
     * @return
     */
    boolean login(String name, String password);

    /**
     * 修改密码
     * @param name
     * @param password
     * @param newpassword
     * @return
     */
    boolean revise(String name, String password, String newpassword);

    /**
     * 以管理员的身份登陆系统
     * @param name
     * @param password
     * @return
     */
    boolean login1(String name, String password);
}
