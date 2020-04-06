package com.zhj.event.view;

import com.zhj.event.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Revise implements ActionListener {
    private JPanel pan = new JPanel();  //创建JPanel面板对象
    private  static JFrame jf = new JFrame("欢迎来到英雄联盟职业联赛");
    private JLabel jl = new JLabel("修改赛事");
    private JLabel datelab = new JLabel("日期");
    private JLabel againstlab = new JLabel("对阵");
    private JTextField datetext = new JTextField();
    private JTextField againsttext = new JTextField();
    GameController gameController = new GameController();

    public JButton affirm = new JButton("确认");
    public JButton resert = new JButton("重置");

    public Revise(){
        Font font = new Font("宋体", Font.BOLD, 12);  //创建Font对象，并初始化font的字体名，风格和大小
        Font font1 = new Font("宋体", Font.BOLD, 16);
        pan.setLayout(null);  //使该窗体（or面板）取消布局管理器设置
        jl.setBounds(250, 70, 200, 30);//设置图标的位置和大小
        datelab.setBounds(150, 150, 60, 30);
        datetext.setBounds(250, 150, 200, 30);
        againstlab.setBounds(150, 200, 60, 30);
        againsttext.setBounds(250, 200, 200, 30);
        affirm.setBounds(175, 300, 100, 30);
        resert.setBounds(320, 300, 100, 30);

        pan.add(jl);
        pan.add(datelab);
        pan.add(datetext);
        pan.add(againstlab);
        pan.add(againsttext);
        pan.add(affirm);
        pan.add(resert);

        affirm.addActionListener(this);
        resert.addActionListener(this);

        affirm.setFont(font);
        resert.setFont(font);
        jl.setFont(font1);

        jf.add(pan);
        jf.setSize(600, 500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new Revise();
    }

    public static void closeThis() {
        jf.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == affirm){
            affirm();
        }else if(e.getSource() == resert){
            resert();
        }
    }

    public void affirm(){
        Boolean result = gameController.revise(datetext.getText(),againsttext.getText());
        if(result){
            jf.setVisible(false);
        }
    }

    public void resert(){
        datetext.setText("");
        againsttext.setText("");
    }
}
