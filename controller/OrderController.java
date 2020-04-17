package com.zhj.event.controller;

import com.zhj.event.service.OrderService;
import com.zhj.event.service.impl.OrderServiceImpl;

public class OrderController implements OrderService{

    OrderService orderService = new OrderServiceImpl();

    /**
     * 通过用户名获得用户的id
     * @param name 账号
     * @return int
     */
    public int getUserIdByName(String name) {
        int result = orderService.getUserIdByName(name);
        return result;
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
        Boolean result = orderService.reserve(userId,gameId,date,hostTeam,guestTeam,price);
        return result;
    }

    /**
     * 遍历该用户的订单表
     * @param userId 用户id
     * @return boolean
     */
    public boolean queryOrder(int userId) {
        Boolean result = orderService.queryOrder(userId);
        return result;
    }

    /**
     * 取消订单
     * @param userId 用户id
     * @param gameId 赛事id
     * @return boolean
     */
    public boolean cancelOrder(int userId, int gameId) {
        Boolean result = orderService.cancelOrder(userId,gameId);
        return result;
    }
}
