package com.zhj.event.dao;

public interface OrderDao {
    int getUserIdByName(String name);

    boolean insertOrder(int userId, int gameId,String date,String hostTeam,String guestTeam,int price);

    boolean queryOrder(int userId);

    boolean cancelOrder(int userId, int gameId);
}
