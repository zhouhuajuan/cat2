package com.zhj.event.view;

import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.UserDaoImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: cat
 * @description: 我的钱包类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class MyWallet implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel balanceLab = new JLabel("我的余额：");
    JLabel chargeLab = new JLabel("我要充值：");
    JLabel balanceLab1;
    JLabel tipLab = new JLabel("你的钱包空空，快去充钱吧！");
    JComboBox comboBox;

    UserController userController = new UserController();
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    JButton charge = new JButton("充值");
    JButton back = new JButton("返回主页");

    /**
     * 定义一个静态的String类型成员变量
     */
    static String name;

    /**
     * 定义全局变量userId和balance
     */
    int userId;
    int balance;

    public MyWallet(){
        //定义一个下拉复选框
        String[] price = {"30","50","100","200","300","500"};
        comboBox = new JComboBox(price);

        //调用getUserIdByName()方法获得userId
        int result = userController.getUserIdByName(name);
        if(result == 1){
            userId = userDaoImpl.userId;
            //调用getBalanceByUserId()方法获得balance
            Boolean result1 = userController.getBalanceByUserId(userId);
            if(result1){
                balance = userDaoImpl.balance;
                String balance1 = String.valueOf(balance);
                balanceLab1 = new JLabel(balance1);
            }
        }

        //使panel取消布局管理器
        panel.setLayout(null);
        tipLab.setBounds(230,50,200,30);
        balanceLab.setBounds(100,100,100,30);
        balanceLab1.setBounds(200,100,150,30);
        chargeLab.setBounds(100,200,100,30);
        comboBox.setBounds(200,200,100,30);
        charge.setBounds(350,200,90,30);
        back.setBounds(250,350,100,30);

        //为按钮添加监听事件
        charge.addActionListener(this);
        back.addActionListener(this);

        //判断余额是否为0，如果为0则添加标签tipLab
        if(balance == 0){
            panel.add(tipLab);
        }

        //为panel添加组件
        panel.add(balanceLab);
        panel.add(balanceLab1);
        panel.add(chargeLab);
        panel.add(comboBox);
        panel.add(charge);
        panel.add(back);

        frame.add(panel);
        frame.setTitle("我的钱包");
        frame.setSize(600,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyWallet();
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
        if(e.getSource() == charge){
            charge();
        }else if(e.getSource() == back){
            closeThis();
            new HomePage();
        }
    }

    //点击充值按钮处理事件
    public void charge(){
        String money = (String) comboBox.getSelectedItem();
        int total = Integer.parseInt(money) + balance;
        System.out.println(total);
        Boolean result = userController.chargeMoney(userId,total);
        if(result){
            JOptionPane.showMessageDialog(null, "充值成功！");
            closeThis();
            new MyWallet();
        }else {
            JOptionPane.showMessageDialog(null, "充值失败！");
        }
    }
}
