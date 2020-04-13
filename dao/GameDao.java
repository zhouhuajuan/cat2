package com.zhj.event.dao;

public interface GameDao {
    /**
     * 添加赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    boolean insertGame(String date, String host_team,String guest_team,String price);

    /**
     * 修改赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    boolean updateGame(String date, String host_team,String guest_team,String price);

    /**
     * 删除赛事
     * @param date
     */
    boolean deleteGame(String date);

    boolean queryAnyGame(String text);
}
