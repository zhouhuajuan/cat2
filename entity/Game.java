package com.zhj.event.entity;

public class Game {
    private int id;
    private String date;
    private String host_team;
    private String guest_team;
    private String price;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getHost_team() {
        return host_team;
    }
    public void setHost_team(String host_team) {
        this.host_team = host_team;
    }
    public String getGuest_team() {
        return guest_team;
    }
    public void setGuest_team(String guest_team) {
        this.guest_team = guest_team;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
