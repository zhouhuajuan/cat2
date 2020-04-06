package com.zhj.event.view;

import com.zhj.event.view.Add;
import com.zhj.event.view.Delete;
import com.zhj.event.view.Revise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage2 implements ActionListener {

    private JPanel pan = new JPanel();  //创建JPanel面板对象
    private static JFrame jf = new JFrame();
    private JLabel jl = new JLabel("英雄联盟职业联赛");

    public JButton add = new JButton("添加");
    public JButton revise = new JButton("修改");
    public JButton delete = new JButton("删除");

    public HomePage2() {
        Font font = new Font("宋体", Font.BOLD, 12);  //创建Font对象，并初始化font的字体名，风格和大小
        Font font1 = new Font("宋体", Font.BOLD, 16);
        jf.setTitle("欢迎来到英雄联盟职业联赛");
        pan.setLayout(null);  //使该窗体（or面板）取消布局管理器设置
        jl.setBounds(230, 70, 200, 30);//设置图标的位置和大小
        add.setBounds(250, 300, 100, 30);
        revise.setBounds(250, 350, 100, 30);
        delete.setBounds(250, 400, 100, 30);

        pan.add(jl);
        pan.add(add);
        pan.add(revise);
        pan.add(delete);

        add.setFont(font);
        revise.setFont(font);
        delete.setFont(font);
        jl.setFont(font1);

        add.addActionListener(this);
        revise.addActionListener(this);
        delete.addActionListener(this);

        jf.add(pan);
        jf.setSize(600, 500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage2();
    }

    public static void closeThis() {
        jf.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == add){
            add();
        }else if(e.getSource() == revise){
            revise();
        }else if(e.getSource() == delete){
            delete();
        }
    }

    //点击添加按钮处理事件
    public void add(){
        closeThis();
        new Add();
    }

    //点击修改按钮处理事件
    public void revise(){
        closeThis();
        new Revise();
    }

    //点击删除按钮处理事件
    public void delete(){
        closeThis();
        new Delete();
    }
}
