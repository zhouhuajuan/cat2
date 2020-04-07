package com.zhj.event.dao.impl;

import com.zhj.event.dao.UserDao;

import javax.swing.*;
import java.sql.*;

public class UserDaoImpl implements UserDao {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/cat?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
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
    public int insert(String username, String password) throws SQLException {
        String sql = "select username from user where username=? ";
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        String sql1 = "insert into user(username,password) values(\"" + username + "\",\"" + password + "\")";
        if (rs.next()) {
            JOptionPane.showMessageDialog(null, "对不起该用户已存在！");
        } else {
            int a = preparedStatement.executeUpdate(sql1);
            con.close();
            preparedStatement.close();
            return a;
        }
        return 0;
    }

    //对用户信息的修改实际上就是对密码的修改
    public Boolean update(String username, String password, String newpassword) throws SQLException {
        String sql = "select password from user where username=? ";
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, username);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            String sql1 = "update user set password=\"" + newpassword + "\"where username=\"" + username + "\"";
            try {
                int a = preparedStatement.executeUpdate(sql1);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "密码修改成功！");
                }
                con.close();
                preparedStatement.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "用户不存在！");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "修改失败");
        }
        return false;
    }

    //对比用户名和密码是否匹配
    public Boolean compare(String username, String password) {
        boolean m = false;
        String sql = "select password from user where username = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                String pa = res.getString(1);
                System.out.println(pa + " " + password);
                if (pa.equals(password)) {
                    m = true;
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误！");
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
        return m;
    }
}
