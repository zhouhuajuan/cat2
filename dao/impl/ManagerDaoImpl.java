package com.zhj.event.dao.impl;

import com.zhj.event.dao.ManagerDao;

import javax.swing.*;
import java.sql.*;

public class ManagerDaoImpl implements ManagerDao {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/cat?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
    String name = "root";
    String passwd =null;
    private Object String;

    public ManagerDaoImpl() {
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

    //对比管理员名和密码是否匹配
    public Boolean compare(String managername, String password) {
        boolean m = false;
        String sql = "select password from manager where managername=?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,managername);
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
                JOptionPane.showMessageDialog(null, "管理员名不存在！");
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
