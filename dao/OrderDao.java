package com.zhj.event.dao;

public interface OrderDao {

    /**
     * 通过用户名获得用户的id
     * @param name 账号
     * @return int
     */
    int getUserIdByName(String name);

    /**
     * 预定赛事
     * @param userId 用户id
     * @param gameId 赛事id
     * @param date 日期
     * @param hostTeam 主战队
     * @param guestTeam 客战队
     * @param price 价格
     * @return boolean
     */
    boolean insertOrder(int userId, int gameId,String date,String hostTeam,String guestTeam,int price);

    /**
     * 遍历该用户的订单表
     * @param userId 用户id
     * @return boolean
     */
    boolean queryOrder(int userId);

    /**
     * 取消订单
     * @param userId 用户id
     * @param gameId 赛事id
     * @return boolean
     */
    boolean cancelOrder(int userId, int gameId);
}
