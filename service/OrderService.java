package com.zhj.event.service;

public interface OrderService {
    int getUserIdByName(String name);

    boolean reserve(int userId, int gameId,String date,String hostTeam,String guestTeam,int price);

    boolean queryOrder(int userId);

    boolean cancelOrder(int userId, int gameId);
}
