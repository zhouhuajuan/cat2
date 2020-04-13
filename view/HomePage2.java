package com.zhj.event.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage2 implements ActionListener {

    private JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();
    private JLabel jl = new JLabel("英雄联盟职业联赛");

    public JButton add = new JButton("添加");
    public JButton revise = new JButton("修改");
    public JButton delete = new JButton("删除");

    public HomePage2() {
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);
        frame.setTitle("欢迎来到英雄联盟职业联赛");
        panel.setLayout(null);
        jl.setBounds(230, 70, 200, 30);
        add.setBounds(250, 300, 100, 30);
        revise.setBounds(250, 350, 100, 30);
        delete.setBounds(250, 400, 100, 30);

        panel.add(jl);
        panel.add(add);
        panel.add(revise);
        panel.add(delete);

        add.setFont(font);
        revise.setFont(font);
        delete.setFont(font);
        jl.setFont(font1);

        add.addActionListener(this);
        revise.addActionListener(this);
        delete.addActionListener(this);

        frame.add(panel);
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
