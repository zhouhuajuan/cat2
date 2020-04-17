package com.zhj.event.dao;

public interface MessageDao {

    /**
     * 保存个人信息
     * @param userId 用户id
     * @param name 赛事id
     * @param sex 性别
     * @param signature 个性签名
     * @return boolean
     */
    boolean inserMessage(int userId, String name, String sex, String signature);
}
