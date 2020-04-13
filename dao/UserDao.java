package com.zhj.event.dao;

public interface UserDao {

    /**
     *注册账号
     * @param name
     * @param password
     * @return
     */
    Boolean insertUser(String name, String password);

    /**
     *以用户的身份登陆系统
     * @param name
     * @param password
     * @return
     */
    Boolean compareUserByPassword(String name, String password);

    /**
     *修改密码
     * @param name
     * @param password
     * @param newpassword
     * @return
     */
    Boolean changePassword(String name, String password, String newpassword);

    /**
     * 以管理员的身份登陆系统
     * @param name
     * @param password
     * @return
     */
    Boolean compareUserByPassword1(String name, String password);
}
