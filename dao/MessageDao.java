package com.zhj.event.dao;

public interface MessageDao {
    boolean inserMessage(int userId, String name, String sex, String signature);
}
