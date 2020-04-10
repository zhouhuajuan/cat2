package com.zhj.event.service;

public interface GameService {
    boolean add(String date, String against);

    boolean revise(String date, String against);

    void delete(String date, String against);

}
