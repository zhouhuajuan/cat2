package com.zhj.event.service;

public interface GameService {
    /**
     * 添加赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个boolean结果
     */
    boolean add(String date, String host_team,String guest_team,int price);

    /**
     * 修改赛事
     * @param date 日期
     * @param host_team 主战队
     * @param guest_team 客战队
     * @param price 价格
     * @return 返回一个boolean结果
     */
    boolean revise(String date,String host_team,String guest_team,int price);

    /**
     * 删除赛事
     * @param date 日期
     * @return 返回一个boolean结果
     */
    boolean delete(String date);

    /**
     * 查询赛事
     * @param text 搜索框文本
     * @return 返回一个boolean结果
     */
    boolean search(String text);
}
