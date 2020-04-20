package com.zhj.event.view;

import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.UserDaoImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWallet implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel balancelab = new JLabel("我的余额：");
    private JLabel chargelab = new JLabel("我要充值：");
    private JLabel balancelab1;
    private JLabel tiplab = new JLabel("你的钱包空空，快去充钱吧！");
    private JComboBox comboBox;
    UserController userController = new UserController();
    UserDaoImpl userDaoImpl = new UserDaoImpl();

    public JButton charge = new JButton("充值");
    public JButton back = new JButton("返回主页");
    public static String name;
    public int userId;
    public int balance;

    //下拉列表框的另一种写法
    //jBox=new JComboBox<String>();
	//jBox.addItem("超级用户");

    public MyWallet(){
        frame.setTitle("我的钱包");
        String[] price = {"30","50","100","200","300","500"};
        comboBox = new JComboBox(price);

        int result = userController.getUserIdByName(name);
        if(result == 1){
            userId = userDaoImpl.userId;
            Boolean result1 = userController.getBalanceByUserId(userId);
            if(result1){
                balance = userDaoImpl.balance;
                String balance1 = String.valueOf(balance);
                balancelab1 = new JLabel(balance1);
            }
        }

        panel.setLayout(null);
        tiplab.setBounds(230,50,200,30);
        balancelab.setBounds(100,100,100,30);
        balancelab1.setBounds(200,100,150,30);
        chargelab.setBounds(100,200,100,30);
        comboBox.setBounds(200,200,100,30);
        charge.setBounds(350,200,90,30);
        back.setBounds(250,350,100,30);

        charge.addActionListener(this);
        back.addActionListener(this);

        if(balance == 0){
            panel.add(tiplab);
        }

        panel.add(balancelab);
        panel.add(balancelab1);
        panel.add(chargelab);
        panel.add(comboBox);
        panel.add(charge);
        panel.add(back);

        frame.add(panel);
        frame.setSize(600,500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyWallet();
    }

    public void closeThis() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == charge){
            charge();
        }else if(e.getSource() == back){
            closeThis();
            new HomePage();
        }
    }

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
