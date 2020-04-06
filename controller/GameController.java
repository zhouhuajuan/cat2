package com.zhj.event.controller;

import com.zhj.event.service.GameService;
import com.zhj.event.service.impl.GameServiceImpl;

import java.sql.SQLException;

public class GameController {

    GameService gameService = new GameServiceImpl();
    public Boolean add(String date, String against) throws SQLException {
        Boolean result = gameService.add(date,against);
        return result;
    }

    public Boolean revise(String date, String against) {
        Boolean result = gameService.revise(date,against);
        return result;
    }

    public void delete(String date, String against) {
        gameService.delete(date,against);
        return;
    }
}
