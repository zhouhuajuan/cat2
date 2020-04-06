package com.zhj.event.service.impl;

import com.zhj.event.dao.UserDao;
import com.zhj.event.dao.impl.UserDaoImpl;
import com.zhj.event.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao =new UserDaoImpl();

    @Override
    public boolean register(String name, String password) {
        int row = 0;
        try {
            row = userDao.insert(name,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(row == 1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean login(String name, String password) {
        Boolean result = userDao.compare(name, password);
        return result;
    }

    @Override
    public boolean update(String name, String password, String newpassword) {
        Boolean result = null;
        try {
            result = userDao.update(name, password, newpassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
