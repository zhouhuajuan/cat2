package com.zhj.event.view;

import com.zhj.event.controller.UserController;
import com.zhj.event.util.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register implements ActionListener {
    private JPanel pan = new JPanel();
    private static JFrame jf = new JFrame("注册");
    private JLabel namelab = new JLabel("账号");
    private JTextField nametext = new JTextField();
    private JLabel passwordlab = new JLabel("密码");
    private JTextField passwordtext = new JTextField();
    private JLabel label1 = new JLabel("请输入你的手机号码进行注册！");
    private JLabel label2 = new JLabel("请输入8~16位数字字母组合来设置密码！");
    UserController userController = new UserController();

    public JButton register = new JButton("注册");

    public Register(){
        Font font = new Font("宋体", Font.BOLD, 11);
        pan.setLayout(null);
        namelab.setBounds(70, 30, 60, 30);
        nametext.setBounds(140, 30, 140, 30);
        label1.setBounds(140,60,250,30);
        passwordlab.setBounds(70, 110, 60, 30);
        passwordtext.setBounds(140, 110, 140, 30);
        label2.setBounds(140,140,250,30);
        register.setBounds(170, 200, 60, 30);

        label1.setFont(font);
        label2.setFont(font);

        pan.add(namelab);
        pan.add(nametext);
        pan.add(label1);
        pan.add(passwordlab);
        pan.add(passwordtext);
        pan.add(label2);
        pan.add(register);

        register.addActionListener(this);

        jf.add(pan);
        jf.setSize(400,300);
        jf.setVisible(true);

    }

    public static void main(String[] args) {
        new Register();
    }

    public static void closeThis() {
        jf.dispose();
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (nametext.getText() == null || nametext.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "注册账号为空，请重新输入！");
        }else {
            //校验输入的账号：1、11位纯数字2、以1开头必须以3578做第二位
            Pattern pattern =Pattern.compile("1[3,5,7,8]\\d{9}");
            Matcher isNum = pattern.matcher(nametext.getText());
            //校验输入的密码是否为8~16数字字母组合
            String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(passwordtext.getText());
            if(!isNum.matches() || !matcher.matches()){
                JOptionPane.showMessageDialog(null, "账号或密码的输入格式不符合！");
            }else {
                MD5Util md5Util = new MD5Util();
                String password = null;
                try {
                    password = md5Util.md5(passwordtext.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Boolean result = userController.register(nametext.getText(), password);
                if (result) {
                    JOptionPane.showMessageDialog(null, "注册成功！");
                    HomePage.name = nametext.getText();
                    SearchGame.name = nametext.getText();
                    closeThis();
                    new HomePage();
                }else {
                    JOptionPane.showMessageDialog(null, "对不起该用户已存在！");
                }
            }
        }
    }
}