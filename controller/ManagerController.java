package com.zhj.event.controller;

import com.zhj.event.service.ManagerService;
import com.zhj.event.service.impl.ManagerServiceImpl;

public class ManagerController {
    ManagerService managerService = new ManagerServiceImpl();
    public boolean login(String name, String password) {
        Boolean result = managerService.login(name,password);
        return result;
    }
}
