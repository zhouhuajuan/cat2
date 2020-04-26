package com.zhj.event.controller;

import com.zhj.event.service.OrderService;
import com.zhj.event.service.impl.OrderServiceImpl;

public class OrderController implements OrderService{

    /**
     * 实例化一个OrderService对象
     */
    OrderService orderService = new OrderServiceImpl();

    /**
     * 通过用户名获得用户的id
     * @param name 账号
     * @return int
     */
    public int getUserIdByName(String name) {
        return orderService.getUserIdByName(name);
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
    public boolean reserve(int userId, int gameId,String date, String hostTeam, String guestTeam,int price) {
        return orderService.reserve(userId,gameId,date,hostTeam,guestTeam,price);
    }

    /**
     * 遍历该用户的订单表
     * @param userId 用户id
     * @return boolean
     */
    public boolean queryOrder(int userId) {
        return orderService.queryOrder(userId);
    }

    /**
     * 取消订单
     * @param userId 用户id
     * @param gameId 赛事id
     * @return boolean
     */
    public boolean cancelOrder(int userId, int gameId) {
        return orderService.cancelOrder(userId,gameId);
    }
}
