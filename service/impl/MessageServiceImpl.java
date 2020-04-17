package com.zhj.event.service.impl;

import com.zhj.event.dao.MessageDao;
import com.zhj.event.dao.impl.MessageDaoImpl;
import com.zhj.event.service.MessageService;

public class MessageServiceImpl implements MessageService {

    MessageDao messageDao = new MessageDaoImpl();

    /**
     * 保存用户信息
     * @param userId 用户id
     * @param name 昵称
     * @param sex 性别
     * @param signature 个性签名
     * @return boolean
     */
    @Override
    public boolean inserMessage(int userId,String name, String sex, String signature) {
        Boolean result = messageDao.inserMessage(userId,name,sex,signature);
        return result;
    }
}
