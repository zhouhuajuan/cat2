package com.zhj.event.dao.impl;

import com.zhj.event.dao.GameDao;
import com.zhj.event.view.Delete;
import com.zhj.event.view.HomePage2;
import com.zhj.event.view.Revise;

import javax.swing.*;
import java.sql.*;

public class GameDaoImpl implements GameDao {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/cat?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
    String name = "root";
    String passwd =null;
    private Object String;

    public GameDaoImpl() {
        try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url, name, passwd);
            preparedStatement = (PreparedStatement) con.createStatement();

        } catch (ClassNotFoundException e) {
            System.out.println("对不起，找不到这个Driver");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //添加赛事信息
    public int insert1(String date, String against) throws SQLException {
        if (date == null || date.trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "添加赛事信息为空，请重新输入！");
            return 0;
        }

        // 编写sql语句
        String sql="select against from competition where against='"+against+"' ";
        String sql1 = "insert into competition(date ,against) values(\"" + date + "\",\"" + against + "\")";
        // 执行sql语句
        ResultSet rs=preparedStatement.executeQuery(sql);
        if(rs.next()) {
            JOptionPane.showMessageDialog(null, "对不起该赛事已存在！");
        }else {
            int a = preparedStatement.executeUpdate(sql1);
            con.close();
            preparedStatement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "添加成功！");
               return 0;
            }
        }
        return 0;
    }

    //修改赛事信息
    public boolean update(String date, String against) throws SQLException {
        boolean judge = false;
        String sql1 = "select date from competition where against =\"" + against +"\"";
        ResultSet rs=preparedStatement.executeQuery(sql1);
        if (rs.next()) {
            String sql = "update competition set date =\"" + date + "\"where against=\"" + against + "\"";
            try {
                int a = preparedStatement.executeUpdate(sql);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "赛事修改成功！");
                    judge = true;
                    Revise.closeThis();
                    new HomePage2();
                }
                con.close();
                preparedStatement.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "修改失败！");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "该赛事不存在！");
        }
        return judge;
    }

    //删除赛事信息
    public void delete(String date, String against) throws SQLException {
        String sql="select against from competition where against=\""+against+"\"   ";
        String sql1 = "delete from competition where against=\"" + against + "\"";
        ResultSet rs=preparedStatement.executeQuery(sql);
        if(rs.next()) {
            int a = preparedStatement.executeUpdate(sql1);
            con.close();
            preparedStatement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "删除成功！");
            }
        }else{
            JOptionPane.showMessageDialog(null, "该赛事不存在！");
        }

    }

    //对比赛事信息是否匹配
    public boolean compare2(String date, String against) {
        boolean m = false;
        String sql = "select date from competition where against=\"" + against + "\"";
        try {
            res = preparedStatement.executeQuery(sql);
            if (res.next()) {
                String pa = res.getString(1);
                System.out.println(pa + " " + date);
                if (pa.equals(date)) {
                    m = true;
                } else {
                    JOptionPane.showMessageDialog(null, "日期错误！");
                }
            } else {
                JOptionPane.showMessageDialog(null, "赛事不存在！");
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
