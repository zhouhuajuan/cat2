package com.zhj.event.service.impl;

import com.zhj.event.dao.GameDao;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.service.GameService;

import java.sql.SQLException;
import java.util.Vector;

public class GameServiceImpl implements GameService {
    //实例化一个GameDao对象
    GameDao gameDao = new GameDaoImpl();

    /**
     * 添加赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个Boolean结果
     */
    @Override
    public boolean add(String date, String host_team,String guest_team,String price) {
        Boolean result = gameDao.insertGame(date,host_team,guest_team,price);
        return result;
    }

    /**
     * 修改赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个Boolean结果
     */
    public boolean revise(String date, String host_team,String guest_team,String price) {
        Boolean result = gameDao.updateGame(date,host_team,guest_team,price);
        return result;
    }

    /**
     * 删除赛事
     * @param date 日期
     * @return 返回一个Boolean结果
     */
    @Override
    public boolean delete(String date) {
        Boolean result = gameDao.deleteGame(date);
        return result;
    }

    /**
     * 查询赛事
     * @param text 搜索框文本
     * @return 返回一个Boolean结果
     */
    @Override
    public boolean search(String text) {
        Boolean result = gameDao.queryAnyGame(text);
        return result;
    }
}
