package com.zhj.event.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnect {
    //使用静态内部类实现单例模式
    private JdbcConnect(){};

    private static class SingleJdbcConnect{
        public static final JdbcConnect jdbcConnect = new JdbcConnect();
    }

    public static JdbcConnect getJdbcConnectInstance(){
        return JdbcConnect.SingleJdbcConnect.jdbcConnect;
    }

    private static final String URL = "jdbc:mysql://localhost:3306/dajuan?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
    private static final String NAME = "root";
    private static final String PASSWORD = null;

    /**
     * 使用DriverManager获取数据库连接Connection
     * @return java.sql.Connection
     */
    public synchronized Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL,NAME,PASSWORD);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放数据库连接Connection
     * @param connection
     */
    public synchronized void freeConnection(Connection connection){
        try {
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
