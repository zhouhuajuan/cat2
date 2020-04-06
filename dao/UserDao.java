package com.zhj.event.dao;

import java.sql.SQLException;

public interface UserDao {

    int insert(String name, String password) throws SQLException;

    Boolean compare(String name, String password);

    Boolean update(String name, String password, String newpassword) throws SQLException;
}
