package com.zhj.event.controller;

import com.zhj.event.service.OrderService;
import com.zhj.event.service.impl.OrderServiceImpl;

public class OrderController implements OrderService{

    OrderService orderService = new OrderServiceImpl();
    public int getUserIdByName(String name) {
        int result = orderService.getUserIdByName(name);
        return result;
    }



    public boolean reserve(int userId, int gameId,String date, String hostTeam, String guestTeam,int price) {
        Boolean result = orderService.reserve(userId,gameId,date,hostTeam,guestTeam,price);
        return result;
    }

    public boolean queryOrder(int userId) {
        Boolean result = orderService.queryOrder(userId);
        return result;
    }

    public boolean cancelOrder(int userId, int gameId) {
        Boolean result = orderService.cancelOrder(userId,gameId);
        return result;
    }
}
