package com.zhj.event.controller;

import com.zhj.event.service.MessageService;
import com.zhj.event.service.impl.MessageServiceImpl;

public class MessageController {

    MessageService messageService = new MessageServiceImpl();
    public boolean insertMessage(int userId,String name, String sex, String signature) {
        Boolean result = messageService.inserMessage(userId,name,sex,signature);
        return result;
    }
}
