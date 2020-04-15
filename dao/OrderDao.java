package com.zhj.event.dao;

public interface OrderDao {
    int getUserIdByName(String name);

    boolean insertOrder(int userId, int gameId);
}
