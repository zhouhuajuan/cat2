package com.zhj.event.dao.impl;

import com.zhj.event.dao.GameDao;
import com.zhj.event.entity.Game;
import com.zhj.event.view.HomePage2;
import com.zhj.event.view.ReviseGame;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class GameDaoImpl implements GameDao {
    Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet res = null;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/dajuan?useSSL=false&serverTimezone=Hongkong&characterEncoding=utf-8&autoReconnect=true";
    String name = "root";
    String passwd =null;


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
    public boolean insertGame(String date, String host_team,String guest_team,String price){
        boolean judge = false;
        String sql="select date,host_team,guest_team from game where date=? and host_team=? and guest_team = ?";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,date);
            preparedStatement.setString(2,host_team);
            preparedStatement.setString(3,guest_team);
            res=preparedStatement.executeQuery();
            String sql1 = "insert into game(date ,host_team,guest_team,price) values(\"" + date + "\",\"" + host_team + "\",\"" + guest_team +"\",\"" + price +"\")";
            if(res.next()) {
                judge = false;
            }else {
                preparedStatement.executeUpdate(sql1);
                con.close();
                preparedStatement.close();
                judge = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    //修改赛事信息
    public boolean updateGame(String date, String host_team,String guest_team,String price){
        boolean judge = false;
        String sql = "select date from game where date =?";
        String sql1 = "update game set host_team =\"" + host_team + "\",guest_team =\"" + guest_team +"\",price=\"" + price +"\" where date =\"" + date + "\"";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,date);
            res=preparedStatement.executeQuery();
            if (res.next()) {
                preparedStatement.executeUpdate(sql1);
                con.close();
                preparedStatement.close();
                judge = true;
            } else {
                judge = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    //删除赛事信息
    public boolean deleteGame(String date) {
        boolean judge = false;
        String sql = "select date from game where date =? ";
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, date);
            res = preparedStatement.executeQuery();
            String sql1 = "delete from game where date =\"" + date + "\"";
            if (res.next()) {
                preparedStatement.executeUpdate(sql1);
                con.close();
                preparedStatement.close();
                judge = true;
            } else {
                judge = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }

    public  Vector rowData, columnName;
    //访问数据库的表获取数据
    public void queryAllGame(){
        String sql = "select * from game";
        rowData = new Vector();
        try {
            preparedStatement = con.prepareStatement(sql);
            res = preparedStatement.executeQuery();
            ResultSetMetaData data = res.getMetaData();

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
            res.close();
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //通过模糊查询获得数据库表的部分数据
    public boolean queryAnyGame(String text){
        boolean judge = false;
        String sql = "select * from game where host_team =? or guest_team =?";
        rowData = new Vector();
        try {
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,text);
            preparedStatement.setString(2,text);
            res = preparedStatement.executeQuery();
            ResultSetMetaData data = res.getMetaData();

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
            res.close();
            preparedStatement.close();
            con.close();
            judge = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return judge;
    }
}
