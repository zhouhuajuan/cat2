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
     * @param newPassword 新密码
     * @return 返回一个boolean结果
     */
    boolean revise(String name, String password, String newPassword) throws Exception;

    /**
     * 以管理员的身份登陆系统
     * @param name 账号
     * @param password 密码
     * @return 返回一个int结果
     */
    int login1(String name, String password);

    /**
     *通过账号获得用户的id
     * @param name 账号
     * @return int
     */
    int getUserIdByName(String name);

    /**
     *通过用户的id获得余额
     * @param userId 用户id
     * @return boolean
     */
    boolean getBalanceByUserId(int userId);

    /**
     * 充值
     * @param userId 用户id
     * @param balance 余额
     * @return boolean
     */
    boolean chargeMoney(int userId, int balance);

    /**
     * 扣费
     * @param userId 用户id
     * @param balance 余额
     * @return boolean
     */
    boolean deductMoney(int userId, int balance);

}
