package com.zhj.event.dao;

public interface UserDao {

    /**
     *注册账号
     * @param name 账号
     * @param password 密码
     * @return 返回一个Boolean结果
     */
    boolean insertUser(String name, String password);

    /**
     *以用户的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    int compareUserByPassword(String name, String password);

    /**
     *修改密码
     * @param name 账号
     * @param password 密码
     * @param newpassword 新密码
     * @return 返回一个Boolean结果
     */
    boolean changePassword(String name, String password, String newpassword);

    /**
     * 以管理员的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    int compareUserByPassword1(String name, String password);
}
