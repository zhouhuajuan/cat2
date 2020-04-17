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
    public boolean reserve(int userId, int gameId,String date,String hostTeam,String guestTeam,int price) {
        boolean result = orderDao.insertOrder(userId,gameId,date,hostTeam,guestTeam,price);
        return result;
    }

    @Override
    public boolean queryOrder(int userId) {
        Boolean result = orderDao.queryOrder(userId);
        return result;
    }

    @Override
    public boolean cancelOrder(int userId, int gameId) {
        Boolean result = orderDao.cancelOrder(userId,gameId);
        return result;
    }
}
