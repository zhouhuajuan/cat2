package com.zhj.event.dao.impl;

import com.zhj.event.dao.OrderDao;
import example.JdbcPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoImpl implements OrderDao {

    public static int userId;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;

    //获取到数据库连接池的单例对象
    JdbcPool jdbcPool = JdbcPool.getJdbcPoolInstance();

    //从连接池中获取到一个数据库连接
    Connection connection = jdbcPool.getJdbcConnection();

    @Override
    public int getUserIdByName(String name) {
        String sql = "select id from user where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            res = preparedStatement.executeQuery();

            if(res.next()){
                preparedStatement.executeUpdate(sql);
                userId = res.getInt("id");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean insertOrder(int userId, int gameId) {
        boolean judge = false;
        String sql="select user_id ,game_id from order where name = ? and game_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,gameId);
            res=preparedStatement.executeQuery();
            String sql1 = "insert into game(user_id ,game_id) values(\"" + userId +"\",\"" + gameId +"\")";
            if(res.next()) {
                judge = false;
            }else {
                preparedStatement.executeUpdate(sql1);
                judge = true;
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }
}
