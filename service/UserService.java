package com.zhj.event.service;

public interface UserService {

    boolean register(String name, String password);

    boolean login(String name, String password);

    boolean update(String name, String password, String newpassword);
}
