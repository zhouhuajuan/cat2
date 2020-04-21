package com.zhj.event.view;

import com.zhj.event.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: cat
 * @description: 修改赛事类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class ReviseGame implements ActionListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("欢迎来到英雄联盟职业联赛");
    JLabel label = new JLabel("修改赛事");
    JLabel dateLab = new JLabel("日期");
    JLabel hostLab = new JLabel("主战队");
    JLabel guestLab = new JLabel("客战队");
    JLabel priceLab = new JLabel("价格");
    JTextField dateText = new JTextField();
    JTextField hostText = new JTextField();
    JTextField guestText = new JTextField();
     JTextField priceText = new JTextField();

    GameController gameController = new GameController();

    JButton affirm = new JButton("确认");
    JButton reset = new JButton("重置");
    JButton back = new JButton("返回主页");

    public ReviseGame(){
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);

        //使该面板取消布局管理器设置
        panel.setLayout(null);
        label.setBounds(250, 30, 200, 30);
        dateLab.setBounds(150, 90, 60, 30);
        dateText.setBounds(250, 90, 200, 30);
        hostLab.setBounds(150, 150, 60, 30);
        hostText.setBounds(250, 150, 200, 30);
        guestLab.setBounds(150, 210, 60, 30);
        guestText.setBounds(250, 210, 200, 30);
        priceLab.setBounds(150, 270, 60, 30);
        priceText.setBounds(250, 270, 200, 30);
        affirm.setBounds(120, 350, 100, 30);
        reset.setBounds(265, 350, 100, 30);
        back.setBounds(405,350,100,30);

        //在panel上添加组件
        panel.add(label);
        panel.add(dateLab);
        panel.add(dateText);
        panel.add(hostLab);
        panel.add(hostText);
        panel.add(guestLab);
        panel.add(guestText);
        panel.add(priceLab);
        panel.add(priceText);
        panel.add(affirm);
        panel.add(reset);
        panel.add(back);

        //为按钮设置监听事件
        affirm.addActionListener(this);
        reset.addActionListener(this);
        back.addActionListener(this);

        //设置按钮和标签的文本风格
        affirm.setFont(font);
        reset.setFont(font);
        back.setFont(font);
        label.setFont(font1);

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ReviseGame();
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
        if(e.getSource() == affirm){
            affirm();
        }else if(e.getSource() == reset){
            dateText.setText("");
            hostText.setText("");
            guestText.setText("");
            priceText.setText("");
        }else if(e.getSource() == back){
            closeThis();
            new HomePage2();
        }
    }

    /**
     * 点击确认按钮触发的事件
     */
    public void affirm(){
        if (dateText.getText() == null || dateText.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "输入框为空，请重新输入！");
        }else {
            int price = Integer.parseInt(priceText.getText());
            Boolean result = gameController.revise(dateText.getText(), hostText.getText(),guestText.getText(),price);
            if(result){
                JOptionPane.showMessageDialog(null, "修改赛事成功！");
            }else {
                JOptionPane.showMessageDialog(null, "该赛事不存在！");
            }
        }
    }
}
