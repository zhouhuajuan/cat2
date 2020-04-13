package com.zhj.event.service.impl;

import com.zhj.event.dao.GameDao;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.service.GameService;

import java.sql.SQLException;

public class GameServiceImpl implements GameService {
    //实例化一个GameDao对象
    GameDao gameDao = new GameDaoImpl();

    /**
     * 添加赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    @Override
    public boolean add(String date, String host_team,String guest_team,String price) {
        Boolean result = gameDao.insertGame(date,host_team,guest_team,price);
        return result;
    }

    /**
     * 修改赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    public boolean revise(String date, String host_team,String guest_team,String price) {
        Boolean result = gameDao.updateGame(date,host_team,guest_team,price);
        return result;
    }

    /**
     * 删除赛事
     * @param date
     */
    @Override
    public boolean delete(String date) {
        Boolean result = gameDao.deleteGame(date);
        return result;
    }

    @Override
    public boolean search(String text) {
        Boolean result = gameDao.queryAnyGame(text);
        return result;
    }

}
