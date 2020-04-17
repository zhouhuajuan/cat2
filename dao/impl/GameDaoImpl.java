package com.zhj.event.dao.impl;

import com.zhj.event.dao.GameDao;
import example.JdbcPool;

import javax.swing.*;
import java.sql.*;
import java.util.Vector;


public class GameDaoImpl implements GameDao {

    PreparedStatement preparedStatement = null;
    ResultSet res = null;

    //获取到数据库连接池的单例对象
    JdbcPool jdbcPool = JdbcPool.getJdbcPoolInstance();

    //从连接池中获取到一个数据库连接
    Connection connection = jdbcPool.getJdbcConnection();

    @Override
    public boolean insertGame(String date, String host_team, String guest_team, String price) {
        boolean judge = false;
        String sql="select date,host_team,guest_team,price from game where date=? and host_team=? and guest_team = ? and price = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,date);
            preparedStatement.setString(2,host_team);
            preparedStatement.setString(3,guest_team);
            preparedStatement.setString(4,price);
            res=preparedStatement.executeQuery();
            String sql1 = "insert into game(date ,host_team,guest_team,price) values(\"" + date + "\",\"" + host_team + "\",\"" + guest_team +"\",\"" + price +"\")";
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

    @Override
    public boolean updateGame(String date, String host_team, String guest_team, String price) {
        boolean judge = false;
        String sql = "select date from game where date =?";
        String sql1 = "update game set host_team =\"" + host_team + "\",guest_team =\"" + guest_team +"\",price=\"" + price +"\" where date =\"" + date + "\"";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,date);
            res=preparedStatement.executeQuery();
            if (res.next()) {
                preparedStatement.executeUpdate(sql1);
                judge = true;
            } else {
                judge = false;
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    @Override
    public boolean deleteGame(String date) {
        boolean judge = false;
        String sql = "select date from game where date =? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date);
            res = preparedStatement.executeQuery();
            String sql1 = "delete from game where date =\"" + date + "\"";
            if (res.next()) {
                preparedStatement.executeUpdate(sql1);
                judge = true;
            } else {
                judge = false;
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    public  Vector rowData, columnName;

    public void queryAllGame(){
        String sql = "select * from game";
        rowData = new Vector();
        try {
            preparedStatement = connection.prepareStatement(sql);
            res = preparedStatement.executeQuery();
            ResultSetMetaData data = res.getMetaData();

            while (res.next()) {
                Vector line1 = new Vector();
                //添加行数据
                for (int k = 1; k <= data.getColumnCount(); k++) {
                    line1.add(res.getString(data.getColumnName(k)));
                }
                rowData.add(line1);
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean queryAnyGame(String text) {
        boolean judge = false;
        String sql = "select * from game where host_team like ? or guest_team like  ?";
        rowData = new Vector();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,text);
            preparedStatement.setString(2,text);
            res = preparedStatement.executeQuery();
            ResultSetMetaData data = res.getMetaData();
            //System.out.println("hello");

            columnName = new Vector();
            //获取列名
            for (int i = 1; i <= data.getColumnCount(); i++) {
                columnName.add(data.getColumnName(i));
            }

            while (res.next()) {
                Vector line1 = new Vector();
                //添加行数据
                for (int k = 1; k <= data.getColumnCount(); k++) {
                    line1.add(res.getString(data.getColumnName(k)));
                }
                rowData.add(line1);
            }
            judge = true;
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

}
