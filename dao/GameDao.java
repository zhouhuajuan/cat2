package com.zhj.event.dao;

public interface GameDao {
    /**
     * 添加赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个boolean结果
     */
    boolean insertGame(String date, String host_team,String guest_team,int price);

    /**
     * 修改赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个boolean结果
     */
    boolean updateGame(String date, String host_team,String guest_team,int price);

    /**
     * 删除赛事
     * @param date 日期
     * @return 返回一个boolean结果
     */
    boolean deleteGame(String date);

    /**
     * 查询数据库的表
     * @param text 搜索框的文本
     * @return 返回一个boolean结果
     */
    boolean queryAnyGame(String text);

}
