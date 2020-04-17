package com.zhj.event.view;

import com.zhj.event.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteGame{
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("欢迎来到英雄联盟职业联赛");
    private JLabel label = new JLabel("删除赛事");
    private JLabel datelab = new JLabel("日期");
    private JTextField datetext = new JTextField();
    GameController gameController = new GameController();

    public JButton affirm = new JButton("确认");
    public JButton back = new JButton("返回主页");

    public DeleteGame(){
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);
        panel.setLayout(null);
        label.setBounds(250, 70, 200, 30);
        datelab.setBounds(150, 150, 60, 30);
        datetext.setBounds(250, 150, 200, 30);
        affirm.setBounds(250, 250, 100, 30);
        back.setBounds(250,300,100,30);

        panel.add(label);
        panel.add(datelab);
        panel.add(datetext);
        panel.add(affirm);
        panel.add(back);

        affirm.setFont(font);
        back.setFont(font);
        label.setFont(font1);

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);

        affirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean result = gameController.delete(datetext.getText());
                if(result){
                    JOptionPane.showMessageDialog(null, "删除赛事成功！");
                }else {
                    JOptionPane.showMessageDialog(null, "对不起该赛事已存在！");
                }
            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeThis();
                new HomePage2();
            }
        });
    }

    public static void main(String[] args) {
        new DeleteGame();
    }

    public void closeThis() {
        frame.dispose();
    }

}
