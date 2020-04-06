package com.zhj.event.view;

import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.view.MyCenters;
import com.zhj.event.view.MyOrders;
import com.zhj.event.view.MyWallet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class HomePage implements ActionListener {
    private JPanel pan = new JPanel();  //创建JPanel面板对象
    private JPanel pan1 = new JPanel();
    private static JFrame jf = new JFrame();

    public JButton jb = new JButton("个人中心");
    public JButton jb1 = new JButton("我的订单");
    public JButton jb2 = new JButton("我的钱包");


    public HomePage() {

       Font font = new Font("宋体", Font.BOLD, 12);  //创建Font对象，并初始化font的字体名，风格和大小
        jf.setTitle("英雄联盟职业联赛");
        pan.setLayout(null);  //使该窗体（or面板）取消布局管理器设置
        jb.setBounds(470, 30, 100, 30);
        jb1.setBounds(470,70,100,30);
        jb2.setBounds(470,110,100,30);

        jb.setFont(font);
        jb1.setFont(font);
        jb2.setFont(font);

        jb.addActionListener(this);
        jb1.addActionListener(this);
        jb2.addActionListener(this);

        pan.add(jb);
        pan.add(jb1);
        pan.add(jb2);

        jf.add(pan);
        jf.setSize(600, 500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }

    public static void closeThis() {
        jf.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb){
            closeThis();
            new MyCenters();
        }else if(e.getSource() == jb1){
            closeThis();
            new MyOrders();
        }else if(e.getSource() == jb2){
            closeThis();
            new MyWallet();
        }
    }

}
