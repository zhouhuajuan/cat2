package com.zhj.event.entity;

/**
 * @program: cat
 * @description: message实体类
 * @author: 周华娟
 * @create: 2020-04-20 20:34
 **/
public class Message {
    private int id;
    private int userId;
    private String name;
    private String sex;
    private String signature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
