package com.zhj.event.service;

public interface MessageService {

    /**
     * 保存个人信息
     * @param userId 用户id
     * @param name 昵称
     * @param sex 性别
     * @param signature 个性签名
     * @return boolean
     */
    boolean inserMessage(int userId,String name, String sex, String signature);

}
