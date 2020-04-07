package com.zhj.event.dao.impl;

import com.zhj.event.dao.GameDao;
import com.zhj.event.entity.Game;
import com.zhj.event.view.HomePage2;
import com.zhj.event.view.Revise;
import example.entity.User;
import example.util.DbUtil;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
    public boolean insert1(String date, String against) throws SQLException {
        boolean judge = false;
        String sql="select against from competition where against=?";
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,against);
        ResultSet rs=preparedStatement.executeQuery();
        String sql1 = "insert into competition(date ,against) values(\"" + date + "\",\"" + against + "\")";
        if(rs.next()) {
            JOptionPane.showMessageDialog(null, "对不起该赛事已存在！");
        }else {
            int a = preparedStatement.executeUpdate(sql1);
            con.close();
            preparedStatement.close();
            if (a == 1) {
                JOptionPane.showMessageDialog(null, "添加成功！");
                judge = true;
            }
        }
        return judge;
    }

    //修改赛事信息
    public boolean update(String date, String against) throws SQLException {
        boolean judge = false;
        String sql = "select date from competition where against =?";
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,against);
        ResultSet rs=preparedStatement.executeQuery();
        if (rs.next()) {
            String sql1 = "update competition set date =\"" + date + "\"where against=\"" + against + "\"";
            try {
                int a = preparedStatement.executeUpdate(sql1);
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
        String sql="select against from competition where against=? ";
        preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1,against);
        ResultSet rs=preparedStatement.executeQuery();

        String sql1 = "delete from competition where against=\"" + against + "\"";
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

    public List<Game> queryAllUser(){
        String sql="select * from competition";
        List<Game> list=new ArrayList<Game>();
        try {
            con= DbUtil.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            System.out.println(preparedStatement.toString());
            while(rs.next()){
                Game user=new Game();
                user.setDate(rs.getString(1));
                user.setAgainst(rs.getString(2));


                list.add(user);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
