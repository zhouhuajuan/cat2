package com.zhj.event.service.impl;

import com.zhj.event.dao.GameDao;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.service.GameService;

import java.sql.SQLException;

public class GameServiceImpl implements GameService {

    GameDao gameDao = new GameDaoImpl();
    @Override
    public boolean add(String date, String against) {
        Boolean result = null;
        try {
            result = gameDao.insert1(date,against);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean revise(String date, String against) {
        Boolean result = null;
        try {
            result = gameDao.update(date,against);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(String date, String against) {
        try {
            gameDao.delete(date,against);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
