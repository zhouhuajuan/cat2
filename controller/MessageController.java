package com.zhj.event.controller;

import com.zhj.event.service.MessageService;
import com.zhj.event.service.impl.MessageServiceImpl;

public class MessageController {

    MessageService messageService = new MessageServiceImpl();

    /**
     * 保存个人信息
     * @param userId 用户id
     * @param name 昵称
     * @param sex 性别
     * @param signature 个性签名
     * @return boolean
     */
    public boolean insertMessage(int userId,String name, String sex, String signature) {
        Boolean result = messageService.inserMessage(userId,name,sex,signature);
        return result;
    }
}
