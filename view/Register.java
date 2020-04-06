package com.zhj.event.view;

import com.zhj.event.controller.UserController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register implements ActionListener {
    private JPanel pan = new JPanel();
    private static JFrame jf = new JFrame("注册");
    private JLabel namelab = new JLabel("账号");
    private JTextField nametext = new JTextField();
    private JLabel passwordlab = new JLabel("密码");
    private JTextField passwordtext = new JTextField();
    UserController userController = new UserController();

    public JButton register = new JButton("注册");

    public Register(){
        pan.setLayout(null);
        namelab.setBounds(70, 30, 60, 30);
        nametext.setBounds(140, 30, 140, 30);
        passwordlab.setBounds(70, 80, 60, 30);
        passwordtext.setBounds(140, 80, 140, 30);
        register.setBounds(170, 200, 60, 30);

        pan.add(namelab);
        pan.add(nametext);
        pan.add(passwordlab);
        pan.add(passwordtext);
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
            Boolean result = userController.register(nametext.getText(), passwordtext.getText());
            if (result) {
                closeThis();
                new HomePage();
            }
        }
    }
}