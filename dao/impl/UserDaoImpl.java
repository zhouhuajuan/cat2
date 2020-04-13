package com.zhj.event.dao.impl;

import com.zhj.event.dao.UserDao;

import javax.swing.*;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/dajuan?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
    String name = "root";
    String passwd = null;
    private Object String;

    public UserDaoImpl() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, name, passwd);

        } catch (ClassNotFoundException e) {
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //用户注册功能的实现，添加数据
    public Boolean insertUser(String name, String password){
        String sql = "select name from user where name =? ";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            String sql1 = "insert into user(name,password) values(\"" + name +"\",\"" + password +"\")";
            if (res.next()) {
                return false;
            } else {
                preparedStatement.executeUpdate(sql1);
                con.close();
                preparedStatement.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //对用户信息的修改实际上就是对密码的修改
    public Boolean changePassword(String name, String password, String newpassword){
        String sql = "select password from user where name =? ";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String sql1 = "update user set password=\"" + newpassword + "\"where name =\"" + name + "\"";
                try {
                    preparedStatement.executeUpdate(sql1);
                    con.close();
                    preparedStatement.close();
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

    //对比用户名和密码是否匹配
    public Boolean compareUserByPassword(String name, String password) {
        boolean judge = false;
        String sql = "select password from user where name = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    judge = true;
                } else {
                    judge = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }
            res.close();
            con.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    //对比管理员名和密码是否匹配
    public Boolean compareUserByPassword1(String name, String password) {
        boolean judge = false;
        String sql = "select password from user where name=? and role = 1";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,name);
            res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    judge = true;
                } else {
                    judge = false;
                }
            } else {
                JOptionPane.showMessageDialog(null, "管理员名不存在！");
            }
            res.close();
            con.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }
}
