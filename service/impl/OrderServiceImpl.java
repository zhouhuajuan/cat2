package com.zhj.event.service.impl;

import com.zhj.event.dao.OrderDao;
import com.zhj.event.dao.impl.OrderDaoImpl;
import com.zhj.event.service.OrderService;

public class OrderServiceImpl implements OrderService {

    /**
     * 实例化一个OrderDao对象
     */
    OrderDao orderDao = new OrderDaoImpl();

    /**
     * 通过用户名获得用户的id
     * @param name 账号
     * @return int
     */
    @Override
    public int getUserIdByName(String name) {
        return orderDao.getUserIdByName(name);
    }

    /**
     * 预定赛事
     * @param userId 用户id
     * @param gameId 赛事id
     * @param date 日期
     * @param hostTeam 主战队
     * @param guestTeam 客战队
     * @param price 价格
     * @return boolean
     */
    @Override
    public boolean reserve(int userId, int gameId,String date,String hostTeam,String guestTeam,int price) {
        return orderDao.insertOrder(userId,gameId,date,hostTeam,guestTeam,price);
    }

    /**
     * 遍历该用户的订单表
     * @param userId 用户id
     * @return boolean
     */
    @Override
    public boolean queryOrder(int userId) {
        return orderDao.queryOrder(userId);
    }

    /**
     * 取消订单
     * @param userId 用户id
     * @param gameId 赛事id
     * @return boolean
     */
    @Override
    public boolean cancelOrder(int userId, int gameId) {
        return orderDao.cancelOrder(userId,gameId);
    }
}
