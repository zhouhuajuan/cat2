package com.zhj.event.dao;

import java.sql.SQLException;

public interface GameDao {
    boolean insert1(String date, String against) throws SQLException;

    boolean update(String date, String against) throws SQLException;

    void delete(String date, String against) throws SQLException;
}
