package com.zhj.event.entity;

/**
 * @program: cat
 * @description: user实体类
 * @author: 周华娟
 * @create: 2020-04-20 20:31
 **/
public class User {
    private int id;
    private String name;
    private String password;
    private int balance;
    private int role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
