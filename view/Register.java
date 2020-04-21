package com.zhj.event.view;

import com.zhj.event.controller.UserController;
import com.zhj.event.util.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cat
 * @description: 注册类
 * @author: 周华娟
 * @create: 2020-04-20 17:45
 **/

public class Register implements ActionListener {
    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame("注册");
    private JLabel nameLab = new JLabel("账号");
    private JTextField nameText = new JTextField();
    private JLabel passLab = new JLabel("密码");
    private JTextField passText = new JTextField();
    private JLabel label1 = new JLabel("请输入你的手机号码进行注册！");
    private JLabel label2 = new JLabel("请输入8~16位数字字母组合来设置密码！");
    UserController userController = new UserController();

    public JButton register = new JButton("注册");

    public Register(){
        //创建Font对象，并初始化font的字体名，风格和大小
        Font font = new Font("宋体", Font.BOLD, 11);

        //使panel取消布局管理器设置
        panel.setLayout(null);
        nameLab.setBounds(70, 30, 60, 30);
        nameText.setBounds(140, 30, 140, 30);
        label1.setBounds(140,60,250,30);
        passLab.setBounds(70, 110, 60, 30);
        passText.setBounds(140, 110, 140, 30);
        label2.setBounds(140,140,250,30);
        register.setBounds(170, 200, 60, 30);

        //设置标签的文本风格
        label1.setFont(font);
        label2.setFont(font);

        //在panel上添加组件
        panel.add(nameLab);
        panel.add(nameText);
        panel.add(label1);
        panel.add(passLab);
        panel.add(passText);
        panel.add(label2);
        panel.add(register);

        //为按钮设置监听事件
        register.addActionListener(this);

        frame.add(panel);
        frame.setSize(400,300);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Register();
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
        if (nameText.getText() == null || nameText.getText().trim().length() <= 0) {
            JOptionPane.showMessageDialog(null, "注册账号为空，请重新输入！");
        }else {
            //校验输入的账号：1、11位纯数字2、以1开头必须以3578做第二位
            Pattern pattern =Pattern.compile("1[3,5,7,8]\\d{9}");
            Matcher isNum = pattern.matcher(nameText.getText());

            //校验输入的密码是否为8~16数字字母组合
            String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(passText.getText());

            if(!isNum.matches() || !matcher.matches()){
                JOptionPane.showMessageDialog(null, "账号或密码的输入格式不符合！");
            }else {
                //实例化一个md5Util对象，调用md5()方法对密码做加密处理
                MD5Util md5Util = new MD5Util();
                String password = null;
                try {
                    password = md5Util.md5(passText.getText());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Boolean result = userController.register(nameText.getText(), password);
                if (result) {
                    JOptionPane.showMessageDialog(null, "注册成功！");
                    //将注册的账号赋值给HomePage和SearchGame两个类的name变量里
                    HomePage.name = nameText.getText();
                    SearchGame.name = nameText.getText();
                    closeThis();
                    new HomePage();
                }else {
                    JOptionPane.showMessageDialog(null, "对不起该用户已存在！");
                }
            }
        }
    }
}