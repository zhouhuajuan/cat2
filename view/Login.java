package com.zhj.event.view;

import com.zhj.event.controller.UserController;
import com.zhj.event.util.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;

/**
 * @author 大娟
 * @ClassName Login.java
 * @Description 登录类
 * @Param
 * @createTime 2020年3月28日16:19
 */

public class Login extends JFrame implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("英雄联盟");
    private JLabel namelab = new JLabel("账    号");
    private JLabel passlab = new JLabel("密    码");
    private JTextField nametext = new JTextField();
    private JPasswordField passtext = new JPasswordField();
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    private int failCount = 0;

    String text;
    UserController userController = new UserController();

    //创建登录、注册和单选按钮
    private JButton login = new JButton("登录");
    public JButton register = new JButton("注册");
    public JRadioButton user = new JRadioButton("用户");
    public JRadioButton manager = new JRadioButton("管理员");

    //定义无参构造方法
    public Login() {

        //创建Font对象，并初始化font的字体名，风格和大小
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体", Font.BOLD, 16);
        frame.setTitle("欢迎登录英雄联盟赛事票务管理系统");

        //使该窗体（or面板）取消布局管理器设置
        panel.setLayout(null);
        label.setBounds(150,25,90,20);
        namelab.setBounds(70, 60, 60, 30);
        nametext.setBounds(140, 60, 140, 30);
        passlab.setBounds(70, 100, 60, 30);
        passtext.setBounds(140, 100, 140, 30);
        user.setBounds(100, 140, 60, 30);
        manager.setBounds(190, 140, 90, 30);
        login.setBounds(80, 190, 90, 20);
        register.setBounds(190, 190, 90, 20);


        panel.add(namelab);
        panel.add(nametext);
        panel.add(passlab);
        panel.add(passtext);
        panel.add(login);
        panel.add(register);
        panel.add(user);
        panel.add(manager);
        panel.add(label);

        buttonGroup1.add(user);
        buttonGroup2.add(manager);

        login.setFont(font);
        register.setFont(font);
        user.setFont(font);
        manager.setFont(font);
        label.setFont(font1);

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

    public void closeThis() {
        frame.dispose();
    }

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

    public void login(){
        if(text.equals("用户")){
            int result = userController.login(nametext.getText(),passtext.getText());
            if(result == 1){
                JOptionPane.showMessageDialog(null, "登陆成功！");
                HomePage.name = nametext.getText();
                SearchGame.name = nametext.getText();
                closeThis();
                new HomePage();
            }else if(result == 2){
                failCount++;
                JOptionPane.showMessageDialog(null, "密码错误！");
            }else if(result == 0){
                JOptionPane.showMessageDialog(null, "用户名不存在！");
            }

            if(failCount >=3){
                JOptionPane.showMessageDialog(Login.this, "登陆失败三次,需进行图形验证!");
                closeThis();
                new CheckCode();
            }
        } else if (text.equals("管理员")) {
            int result = userController.login1(nametext.getText(),passtext.getText());
            if(result == 1){
                JOptionPane.showMessageDialog(null, "登陆成功！");
                closeThis();
                new HomePage2();
            }else if(result == 2){
                JOptionPane.showMessageDialog(null, "密码错误！");
            }else if(result == 0){
                JOptionPane.showMessageDialog(null, "管理员名不存在！");
            }
        }
    }
}