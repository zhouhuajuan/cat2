package com.zhj.event.dao.impl;

import com.zhj.event.dao.MessageDao;
import example.JdbcPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDaoImpl implements MessageDao {
    public static int userId;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;

    //获取到数据库连接池的单例对象
    JdbcPool jdbcPool = JdbcPool.getJdbcPoolInstance();

    //从连接池中获取到一个数据库连接
    Connection connection = jdbcPool.getJdbcConnection();

    public int getUserIdByName(String name) {
        String sql = "select id from user where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            res = preparedStatement.executeQuery();

            if(res.next()){
                //preparedStatement.executeUpdate(sql);
                userId = res.getInt("id");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean inserMessage(int userId, String name, String sex, String signature) {
        String sql = "select * from message where user_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            res = preparedStatement.executeQuery();
            String sql1 = "insert  into message(user_id,name,sex,signature) values (\"" + userId +"\",\"" + name + "\",\"" + sex + "\",\"" + signature +"\")";

            if(res.next()){
                return false;
            }else {
                preparedStatement.executeUpdate(sql1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
