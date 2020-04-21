package com.zhj.event.view;

import com.zhj.event.dao.impl.GameDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * @program: cat
 * @description: 管理员登陆的主页类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class HomePage2 implements ActionListener {

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JTable table;
    JFrame frame = new JFrame();
    /**
     * 定义一个列名的Vector集合
     */
    Vector columnNames = new Vector();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();

    JButton add = new JButton("添加");
    JButton revise = new JButton("修改");
    JButton delete = new JButton("删除");

    public HomePage2() {
        Font font = new Font("宋体", Font.BOLD, 12);

        //设置panel使用流布局管理器，使组件左对齐
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(add);
        panel.add(revise);
        panel.add(delete);

        //设置按钮的文本风格
        add.setFont(font);
        revise.setFont(font);
        delete.setFont(font);

        //为按钮设置监听事件
        add.addActionListener(this);
        revise.addActionListener(this);
        delete.addActionListener(this);

        ////为columnNames集合添加元素
        columnNames.add(0,"id");
        columnNames.add(1,"date");
        columnNames.add(2,"host_team");
        columnNames.add(3,"guest_team");
        columnNames.add(4,"price");

        //调用queryAllGame()方法获得行数据的集合并填充到表格里
        gameDaoImpl.queryAllGame();
        DefaultTableModel defaultTableModel = new DefaultTableModel(gameDaoImpl.rowData,columnNames);
        table = new JTable(defaultTableModel);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);
        table.setFont(new Font(null, Font.PLAIN, 14));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);

        //定义一个滚动面板，并把表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        scrollPane.setPreferredSize(new Dimension(500,400));
        panel1.add(scrollPane);
        panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

        //设置frame使用边界布局管理器
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.SOUTH);
        frame.add(panel1,BorderLayout.NORTH);
        frame.setTitle("欢迎来到英雄联盟职业联赛");
        frame.setSize(600, 500);
        frame.setVisible(true);
        panel1.revalidate();
    }

    public static void main(String[] args) {
        new HomePage2();
    }

    /**
     * 封装关闭窗口的方法
     */
    public void closeThis() {
        frame.dispose();
    }

    /**
     * 重写actionPerformed()方法
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){
            //点击添加按钮处理事件
            closeThis();
            new AddGame();
        }else if(e.getSource() == revise){
            //点击修改按钮处理事件
            closeThis();
            new ReviseGame();
        }else if(e.getSource() == delete){
            //点击删除按钮处理事件
            closeThis();
            new DeleteGame();
        }
    }
}
