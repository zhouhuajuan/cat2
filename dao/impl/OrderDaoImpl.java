package com.zhj.event.dao.impl;

import com.zhj.event.dao.OrderDao;
import com.zhj.event.view.MyOrders;
import example.JdbcPool;

import java.sql.*;
import java.util.Vector;

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
    public boolean insertOrder(int userId, int gameId, String date, String hostTeam, String guestTeam, int price) {
        boolean judge = false;
        String sql="select user_id ,game_id from `order` where user_id  = ? and game_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,gameId);
            res=preparedStatement.executeQuery();
            String sql1 = "insert into `order`(user_id ,game_id,date,host_team,guest_team,price) " +
                    "values(\"" + userId +"\",\"" + gameId +"\",\"" + date +"\",\"" + hostTeam +"\",\"" + guestTeam +"\",\"" + price +"\")";
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

    public Vector rowData;

    @Override
    public boolean queryOrder(int userId) {
        boolean judge = false;
        String sql = "select   game_id,date,host_team,guest_team,price from `order` where user_id = ?";
        rowData = new Vector();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            res = preparedStatement.executeQuery();
            ResultSetMetaData data = res.getMetaData();
            System.out.println("hello6666666");

            while (res.next()) {
                Vector line1 = new Vector();
                //添加行数据
                for (int k = 1; k <= data.getColumnCount(); k++) {
                    line1.add(res.getString(data.getColumnName(k)));
                    //System.out.println(line1);
                    //rowData.add(line1);
                }
                rowData.add(line1);
                //System.out.println(rowData);
                //System.out.println("bababa");
            }
            judge = true;
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    @Override
    public boolean cancelOrder(int userId, int gameId) {
        boolean judge = false;
        String sql="select * from `order` where user_id  = ? and game_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,gameId);
            res=preparedStatement.executeQuery();
            String sql1 ="delete from `order` where user_id = \"" + userId +"\" and game_id = \"" + gameId + "\"";
            if(res.next()) {
                preparedStatement.executeUpdate(sql1);
                judge = true;
            }else {
                judge = false;
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }
}
