package com.zhj.event.service.impl;

import com.zhj.event.dao.OrderDao;
import com.zhj.event.dao.impl.OrderDaoImpl;
import com.zhj.event.service.OrderService;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = new OrderDaoImpl();
    @Override
    public int getUserIdByName(String name) {
       int result = orderDao.getUserIdByName(name);
       return result;
    }

    @Override
    public boolean reserve(int userId, int gameId) {
        boolean result = orderDao.insertOrder(userId,gameId);
        return result;
    }
}
