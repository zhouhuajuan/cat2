package com.zhj.event.service.impl;

import com.zhj.event.dao.UserDao;
import com.zhj.event.dao.impl.UserDaoImpl;
import com.zhj.event.service.UserService;

public class UserServiceImpl implements UserService {

    /**
     * 实例化一个持久层对象
     */
    UserDao userDao =new UserDaoImpl();

    /**
     * 注册账号
     * @param name 账号
     * @param password 密码
     * @return 返回一个Boolean结果
     */
    @Override
    public boolean register(String name, String password) {
        Boolean result = userDao.insertUser(name,password);
        return result;
    }

    /**
     * 以用户的身份登陆系统
     * @param name 账号
     * @param password  密码
     * @return 返回一个int结果
     */
    @Override
    public int login(String name, String password) {
        int result = userDao.compareUserByPassword(name, password);
        return result;
    }

    /**
     * 修改密码
     * @param name 账号
     * @param password 密码
     * @param newPassword 新密码
     * @return 返回一个Boolean结果
     */
    @Override
    public boolean revise(String name, String password, String newPassword) {
        Boolean result = null;
        try {
            result = userDao.changePassword(name, password, newPassword);
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
    @Override
    public int login1(String name, String password) {
        int result = userDao.compareUserByPassword1(name,password);
        return result;
    }

    /**
     *通过账号获得用户的id
     * @param name 账号
     * @return int
     */
    @Override
    public int getUserIdByName(String name) {
        int result = userDao.getUserIdByName(name);
        return result;
    }

    /**
     * 通过用户的id获得余额
     * @param userId 用户id
     * @return boolean
     */
    @Override
    public boolean getBalanceByUserId(int userId) {
        Boolean result = userDao.getBalanceByUserId(userId);
        return result;
    }

    /**
     * 充值
     * @param userId 用户id
     * @param balance 余额
     * @return boolean
     */
    @Override
    public boolean chargeMoney(int userId, int balance) {
        Boolean result = userDao.chargeMoney(userId,balance);
        return result;
    }

    /**
     * 扣费
     * @param userId 用户id
     * @param balance 余额
     * @return boolean
     */
    @Override
    public boolean deductMoney(int userId, int balance) {
        Boolean result =userDao.deductMoney(userId,balance);
        return result;
    }
}
