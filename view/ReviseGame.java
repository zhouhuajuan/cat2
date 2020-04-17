package com.zhj.event.view;

import com.zhj.event.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviseGame implements ActionListener {
    private JPanel panel = new JPanel();
    private  JFrame frame = new JFrame("欢迎来到英雄联盟职业联赛");
    private JLabel label = new JLabel("修改赛事");
    private JLabel datelab = new JLabel("日期");
    private JLabel hostlab = new JLabel("主战队");
    private JLabel guestlab = new JLabel("客战队");
    private JLabel pricelab = new JLabel("价格");
    private JTextField datetext = new JTextField();
    private JTextField hosttext = new JTextField();
    private JTextField guesttext = new JTextField();
    private JTextField pricetext = new JTextField();
    GameController gameController = new GameController();

    public JButton affirm = new JButton("确认");
    public JButton resert = new JButton("重置");
    public JButton back = new JButton("返回主页");

    public ReviseGame(){
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);
        panel.setLayout(null);
        label.setBounds(250, 30, 200, 30);
        datelab.setBounds(150, 90, 60, 30);
        datetext.setBounds(250, 90, 200, 30);
        hostlab.setBounds(150, 150, 60, 30);
        hosttext.setBounds(250, 150, 200, 30);
        guestlab.setBounds(150, 210, 60, 30);
        guesttext.setBounds(250, 210, 200, 30);
        pricelab.setBounds(150, 270, 60, 30);
        pricetext.setBounds(250, 270, 200, 30);
        affirm.setBounds(120, 350, 100, 30);
        resert.setBounds(265, 350, 100, 30);
        back.setBounds(405,350,100,30);

        panel.add(label);
        panel.add(datelab);
        panel.add(datetext);
        panel.add(hostlab);
        panel.add(hosttext);
        panel.add(guestlab);
        panel.add(guesttext);
        panel.add(pricelab);
        panel.add(pricetext);
        panel.add(affirm);
        panel.add(resert);
        panel.add(back);

        affirm.addActionListener(this);
        resert.addActionListener(this);
        back.addActionListener(this);

        affirm.setFont(font);
        resert.setFont(font);
        back.setFont(font);
        label.setFont(font1);

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ReviseGame();
    }

    public void closeThis() {
        frame.dispose();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == affirm){
            affirm();
        }else if(e.getSource() == resert){
            datetext.setText("");
            hosttext.setText("");
            guesttext.setText("");
            pricetext.setText("");
        }else if(e.getSource() == back){
            closeThis();
            new HomePage2();
        }
    }

    public void affirm(){
        if (datetext.getText() == null || datetext.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "输入框为空，请重新输入！");
        }else {
            Boolean result = gameController.revise(datetext.getText(), hosttext.getText(),guesttext.getText(),pricetext.getText());
            if(result){
                JOptionPane.showMessageDialog(null, "修改赛事成功！");
            }else {
                JOptionPane.showMessageDialog(null, "该赛事不存在！");
            }
        }
    }
}
