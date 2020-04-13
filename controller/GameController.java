package com.zhj.event.controller;

import com.zhj.event.service.GameService;
import com.zhj.event.service.impl.GameServiceImpl;

public class GameController {

    //实例化一个GameService对象
    GameService gameService = new GameServiceImpl();

    /**
     * 添加赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    public Boolean add(String date, String host_team,String guest_team,String price){
        Boolean result = gameService.add(date,host_team,guest_team,price);
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
    public Boolean revise(String date, String host_team,String guest_team,String price) {
        Boolean result = gameService.revise(date,host_team,guest_team,price);
        return result;
    }

    /**
     * 删除赛事
     * @param date
     */
    public Boolean delete(String date) {
        Boolean result = gameService.delete(date);
        return result;
    }

    /**
     * 查询赛事
     * @param text
     * @return
     */
    public Boolean search(String text) {
        Boolean result = gameService.search(text);
        return result;
    }
}
