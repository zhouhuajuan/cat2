package com.zhj.event.service.impl;

import com.zhj.event.dao.UserDao;
import com.zhj.event.dao.impl.UserDaoImpl;
import com.zhj.event.service.UserService;

public class UserServiceImpl implements UserService {
    //实例化一个持久层对象
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
     * @param newpassword 新密码
     * @return 返回一个Boolean结果
     */
    @Override
    public boolean revise(String name, String password, String newpassword) {
        Boolean result = userDao.changePassword(name, password, newpassword);
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
}
