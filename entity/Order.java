package com.zhj.event.entity;

import java.sql.Timestamp;

public class Order {
    private Integer id;
    private Timestamp createTime;
    private Timestamp cancelTime;
    private Integer userId;
    private Integer gameId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Timestamp cancelTime) {
        this.cancelTime = cancelTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Order(Timestamp createTime,Integer userId,Integer gameId){
        this.createTime = createTime;
        this.userId = userId;
        this.gameId = gameId;
    }
}
