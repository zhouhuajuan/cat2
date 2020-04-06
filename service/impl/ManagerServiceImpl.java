package com.zhj.event.service.impl;

import com.zhj.event.dao.ManagerDao;
import com.zhj.event.dao.impl.ManagerDaoImpl;
import com.zhj.event.service.ManagerService;

public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao = new ManagerDaoImpl();

    @Override
    public Boolean login(String name, String password) {
        Boolean result = managerDao.compare(name,password);
        return result;
    }
}
