package com.zhj.event.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CheckCode implements ActionListener {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JLabel label;
    private JLabel tiplab = new JLabel("验证码这么清晰，不用刷新了叭~");
    private JTextField textField = new JTextField();

    public JButton affirm = new JButton("确认");

    public CheckCode()  {
        Font font = new Font("宋体", Font.BOLD, 14);

        //生成验证码问答
        char[] bytes = new char[4];
        Random ran = new Random();
        for (int i = 0; i < bytes.length; i++)
        {
            byte bit = (byte)ran.nextInt(62);
            if (bit < 10) bytes[i] = (char)(bit + 48);
            else if (bit < 36) bytes[i] = (char)(bit + 55);
            else bytes[i] = (char)(61 + bit);
        }
        //验证码
        String key = new String(bytes);
        System.out.println(key);

        frame.setTitle("验证码");

        label = new JLabel(key);
        label.setFont(font);

        panel.setLayout(null);
        tiplab.setBounds(100,40,200,20);
        label.setBounds(230,90,80,30);
        textField.setBounds(100,90,100,30);
        affirm.setBounds(140,180,80,25);

        affirm.addActionListener( this);

        panel.add(tiplab);
        panel.add(label);
        panel.add(textField);
        panel.add(affirm);

        frame.add(panel);
        frame.setSize(400,300);
        frame.setVisible(true);
    }


    public static void main(String[] args)  {
        new CheckCode();
    }

    public void closeThis() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(textField.getText().equals(label.getText())){
            JOptionPane.showMessageDialog(null, "验证成功！");
            closeThis();
            new Login();
        }else {
            JOptionPane.showMessageDialog(null, "验证失败！");
            closeThis();
            new CheckCode();
        }
    }
}
