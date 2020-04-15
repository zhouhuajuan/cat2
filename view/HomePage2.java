package com.zhj.event.view;

import com.zhj.event.dao.impl.GameDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage2 implements ActionListener {

    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JTable table;
    private static JFrame frame = new JFrame();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();

    public JButton add = new JButton("添加");
    public JButton revise = new JButton("修改");
    public JButton delete = new JButton("删除");

    public HomePage2() {
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);
        frame.setTitle("欢迎来到英雄联盟职业联赛");
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));

        panel.add(add);
        panel.add(revise);
        panel.add(delete);

        add.setFont(font);
        revise.setFont(font);
        delete.setFont(font);

        add.addActionListener(this);
        revise.addActionListener(this);
        delete.addActionListener(this);

        gameDaoImpl.queryAllGame();
        DefaultTableModel defaultTableModel = new DefaultTableModel(gameDaoImpl.rowData,gameDaoImpl.columnName);
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
        panel1.add(scrollPane);

        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.SOUTH);
        frame.add(panel1,BorderLayout.NORTH);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage2();
    }

    public static void closeThis() {
        frame.dispose();
    }

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
