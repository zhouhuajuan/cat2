package com.zhj.event.view;

import com.zhj.event.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @program: cat
 * @description: 登陆类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class Login extends JFrame implements ActionListener {
    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JLabel label = new JLabel("英雄联盟");
    JLabel nameLab = new JLabel("账    号");
    JLabel passLab = new JLabel("密    码");
    JTextField nameText = new JTextField();
    JPasswordField passText = new JPasswordField();
    ButtonGroup buttonGroup1 = new ButtonGroup();
    ButtonGroup buttonGroup2 = new ButtonGroup();

    /**
     * 设置输错密码的次数
     */
    int failCount = 0;

    /**
     * 设置全局变量text获得单选按钮的文本内容
     */
    String text;
    UserController userController = new UserController();

    JButton login = new JButton("登录");
    JButton register = new JButton("注册");
    JRadioButton user = new JRadioButton("用户");
    JRadioButton manager = new JRadioButton("管理员");

    public Login() {

        //创建Font对象，并初始化font的字体名，风格和大小
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);
        frame.setTitle("欢迎登录英雄联盟赛事票务管理系统");

        //使该窗体（or面板）取消布局管理器设置
        panel.setLayout(null);
        label.setBounds(150,25,90,20);
        nameLab.setBounds(70, 60, 60, 30);
        nameText.setBounds(140, 60, 140, 30);
        passLab.setBounds(70, 100, 60, 30);
        passText.setBounds(140, 100, 140, 30);
        user.setBounds(100, 140, 60, 30);
        manager.setBounds(190, 140, 90, 30);
        login.setBounds(80, 190, 90, 20);
        register.setBounds(190, 190, 90, 20);

        //在panel上添加组件
        panel.add(nameLab);
        panel.add(nameText);
        panel.add(passLab);
        panel.add(passText);
        panel.add(login);
        panel.add(register);
        panel.add(user);
        panel.add(manager);
        panel.add(label);

        //将用户和管理员这两个单选按钮分组
        buttonGroup1.add(user);
        buttonGroup2.add(manager);

        //设置按钮和标签的文本风格
        login.setFont(font);
        register.setFont(font);
        user.setFont(font);
        manager.setFont(font);
        label.setFont(font1);

        //为按钮设置监听事件
        login.addActionListener(this);
        register.addActionListener(this);
        user.addActionListener(this);
        manager.addActionListener(this);

        frame.add(panel);
        frame.setSize(400, 300);
        frame.setVisible(true);

    }


    public static void main(String[] args) {
        new Login();
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
        if(e.getSource() == user){
            text = user.getText();
        }else if(e.getSource() == manager){
            text = manager.getText();
        } else if(e.getSource() == login){
            login();
        }else if(e.getSource() == register){
            closeThis();
            new Register();
        }
    }

    /**
     * 点击登陆按钮触发的事件
     */
    public void login(){
        if(text.equals("用户")){
            int result = userController.login(nameText.getText(),passText.getText());
            if(result == 1){
                JOptionPane.showMessageDialog(null, "登陆成功！");
                HomePage.name = nameText.getText();
                SearchGame.name = nameText.getText();
                closeThis();
                new HomePage();
            }else if(result == 2){
                failCount++;
                JOptionPane.showMessageDialog(null, "密码错误！");
            }else if(result == 0){
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }
            //输错密码三次则须进行图形认证
            if(failCount >=3){
                JOptionPane.showMessageDialog(Login.this, "登陆失败三次,需进行图形验证!");
                closeThis();
                new CheckCode();
            }
        } else if (text.equals("管理员")) {
            int result = userController.login1(nameText.getText(),passText.getText());
            if(result == 1){
                JOptionPane.showMessageDialog(null, "登陆成功！");
                closeThis();
                new HomePage2();
            }else if(result == 2){
                failCount++;
                JOptionPane.showMessageDialog(null, "密码错误！");
            }else if(result == 0){
                JOptionPane.showMessageDialog(null, "管理员名不存在！");
            }
            //输错密码三次则须进行图形认证
            if(failCount >=3){
                JOptionPane.showMessageDialog(Login.this, "登陆失败三次,需进行图形验证!");
                closeThis();
                new CheckCode();
            }
        }
    }
}