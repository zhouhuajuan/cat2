package com.zhj.event.dao.impl;

import com.zhj.event.dao.UserDao;
import com.zhj.event.util.JdbcPool;
import com.zhj.event.util.MD5Util;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    PreparedStatement preparedStatement = null;
    ResultSet res = null;
    public static int userId;
    public static int balance;

    /**
     * 获取到数据库连接池的单例对象
     */
    JdbcPool jdbcPool = JdbcPool.getJdbcPoolInstance();

    /**
     * 从连接池中获取到一个数据库连接
     */
    Connection connection = jdbcPool.getJdbcConnection();

    @Override
    public boolean insertUser(String name,String password) {
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
    public int compareUserByPassword(String name, String password) {
        int judge = 0;
        String sql = "select password from user where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                MD5Util md5Util = new MD5Util();
                String  password1 = md5Util.md5(password);
                System.out.println(pa + " " + password1);
                if (pa.equals(password1)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return judge;
    }

    @Override
    public boolean changePassword(String name, String password, String newPassword){
        MD5Util md5Util = new MD5Util();
        String  newPassword1 = null;
        try {
            newPassword1 = md5Util.md5(newPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select password from user where name =? ";
        String sql1 = "update user set password=\"" + newPassword1 + "\"where name =\"" + name + "\"";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
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
    public int compareUserByPassword1(String name, String password) {
        int judge = 0;
        String sql = "select password from user where name=? and role = 1";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                MD5Util md5Util = new MD5Util();
                String  password1 = md5Util.md5(password);
                System.out.println(pa + " " + password1);
                if (pa.equals(password1)) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return judge;
    }

    @Override
    public int getUserIdByName(String name) {
        String sql = "select id from user where name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,name);
            res = preparedStatement.executeQuery();
            if(res.next()){
                userId = res.getInt("id");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean getBalanceByUserId(int userId) {
        String sql ="select balance from user where id =?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            res = preparedStatement.executeQuery();
            if(res.next()){
                balance = res.getInt("balance");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean chargeMoney(int userId, int balance) {
        String sql ="select balance from user where id =?";
        String sql1="update user set balance = \"" + balance +"\" where id =\"" + userId + "\" ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            res = preparedStatement.executeQuery();
            if(res.next()){
                preparedStatement.executeUpdate(sql1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deductMoney(int userId, int balance) {
        String sql ="select balance from user where id =?";
        String sql1="update user set balance = \"" + balance +"\" where id =\"" + userId + "\" ";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            res = preparedStatement.executeQuery();
            if(res.next()){
                preparedStatement.executeUpdate(sql1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
