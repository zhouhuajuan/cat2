package com.zhj.event.view;

import com.zhj.event.controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: cat
 * @description: 删除赛事类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class DeleteGame{
    JPanel panel = new JPanel();
    JFrame frame = new JFrame("欢迎来到英雄联盟职业联赛");
    JLabel label = new JLabel("删除赛事");
    JLabel dateLab = new JLabel("日期");
    JTextField dateText = new JTextField();
    GameController gameController = new GameController();

    JButton affirm = new JButton("确认");
    JButton back = new JButton("返回主页");

    public DeleteGame(){
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);

        //使该面板取消布局管理器设置
        panel.setLayout(null);
        label.setBounds(250, 70, 200, 30);
        dateLab.setBounds(150, 150, 60, 30);
        dateText.setBounds(250, 150, 200, 30);
        affirm.setBounds(250, 250, 100, 30);
        back.setBounds(250,300,100,30);

        //在panel上添加组件
        panel.add(label);
        panel.add(dateLab);
        panel.add(dateText);
        panel.add(affirm);
        panel.add(back);

        //设置按钮和标签的文本风格
        affirm.setFont(font);
        back.setFont(font);
        label.setFont(font1);

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);

        //删除赛事按钮的处理事件
        affirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean result = gameController.delete(dateText.getText());
                if(result){
                    JOptionPane.showMessageDialog(null, "删除赛事成功！");
                }else {
                    JOptionPane.showMessageDialog(null, "对不起该赛事已存在！");
                }
            }
        });

        //返回主页按钮的处理事件
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

    /**
     * 封装关闭窗口的方法
     */
    public void closeThis() {
        frame.dispose();
    }

}
