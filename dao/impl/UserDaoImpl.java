package com.zhj.event.dao.impl;

import com.zhj.event.dao.UserDao;
import example.JdbcPool;

import javax.swing.*;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    PreparedStatement preparedStatement = null;
    ResultSet res = null;

    //获取到数据库连接池的单例对象
    JdbcPool jdbcPool = JdbcPool.getJdbcPoolInstance();

    //从连接池中获取到一个数据库连接
    Connection connection = jdbcPool.getJdbcConnection();

    @Override
    public boolean insertUser(java.lang.String name, java.lang.String password) {
        String sql = "select name from user where name =? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            String sql1 = "insert into user(name,password) values(\"" + name +"\",\"" + password +"\")";
            if (res.next()) {
                return false;
            } else {
                preparedStatement.executeUpdate(sql1);
                //最后释放连接，将资源交给连接池进行回收
                jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int compareUserByPassword(java.lang.String name, java.lang.String password) {
        int judge = 0;
        String sql = "select password from user where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    judge=1;
                } else {
                    judge=2;
                }
            } else {
                judge=0;
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    @Override
    public boolean changePassword(String name, String password, String newpassword) {
        String sql = "select password from user where name =? ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String sql1 = "update user set password=\"" + newpassword + "\"where name =\"" + name + "\"";
                try {
                    preparedStatement.executeUpdate(sql1);
                    //最后释放连接，将资源交给连接池进行回收
                    jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
                    return true;
                } catch (SQLException e) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int compareUserByPassword1(String name, String password) {
        int judge = 0;
        String sql = "select password from user where name=? and role = 1";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    judge=1;
                } else {
                    judge=2;
                }
            } else {
                judge=0;
            }
            //最后释放连接，将资源交给连接池进行回收
            jdbcPool.releaseJdbcConnection(res,preparedStatement,connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }
}
