package com.zhj.event.service;

public interface OrderService {
    int getUserIdByName(String name);

    boolean reserve(int userId, int gameId);
}
