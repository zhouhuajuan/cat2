package com.zhj.event.service;

public interface GameService {
    /**
     * 添加赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    boolean add(String date, String host_team,String guest_team,String price);

    /**
     * 修改赛事
     * @param date
     * @param host_team
     * @param guest_team
     * @param price
     * @return
     */
    boolean revise(String date,String host_team,String guest_team,String price);

    /**
     * 删除赛事
     * @param date
     */
    boolean delete(String date);

    boolean search(String text);
}
