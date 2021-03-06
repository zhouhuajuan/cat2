package com.zhj.event.controller;

import com.zhj.event.service.GameService;
import com.zhj.event.service.impl.GameServiceImpl;

public class GameController {

    /**
     * 实例化一个GameService对象
     */
    GameService gameService = new GameServiceImpl();

    /**
     * 添加赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个Boolean结果
     */
    public Boolean add(String date, String host_team,String guest_team,int price){
        return gameService.add(date,host_team,guest_team,price);
    }

    /**
     * 修改赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个Boolean结果
     */
    public Boolean revise(String date, String host_team,String guest_team,int price) {
        return gameService.revise(date,host_team,guest_team,price);
    }

    /**
     * 删除赛事
     * @param date 日期
     * @return 返回一个Boolean结果
     */
    public Boolean delete(String date) {
        return gameService.delete(date);
    }

    /**
     * 查询赛事
     * @param text 搜索框文本
     * @return 返回一个Boolean结果
     */
    public Boolean search(String text) {
        return gameService.search(text);
    }
}
