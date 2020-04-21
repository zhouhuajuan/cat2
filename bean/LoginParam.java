package com.zhj.event.bean;

/**
 * @program: cat
 * @description: 存放Login类的参数
 * @author: 周华娟
 * @create: 2020-04-21 20:56
 **/
public class LoginParam {
    private String name;
    private String password;

    public LoginParam(String name,String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
