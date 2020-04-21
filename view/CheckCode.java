package com.zhj.event.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @program: cat
 * @description: 图形验证码类
 * @author: 周华娟
 * @create: 2020-04-20 11:23
 **/

public class CheckCode implements ActionListener {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JLabel label;
    JLabel tiplab = new JLabel("验证码这么清晰，不用刷新了叭~");
    JTextField textField = new JTextField();

    JButton affirm = new JButton("确认");

    public CheckCode()  {
        Font font = new Font("宋体", Font.BOLD, 14);

        //生成验证码
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

        //将验证码存放在标签里
        label = new JLabel(key);
        label.setFont(font);

        //使panel取消布局管理器设置
        panel.setLayout(null);
        tiplab.setBounds(100,40,200,20);
        label.setBounds(230,90,80,30);
        textField.setBounds(100,90,100,30);
        affirm.setBounds(140,180,80,25);

        //为按钮设置监听事件
        affirm.addActionListener( this);

        //在panel上添加组件
        panel.add(tiplab);
        panel.add(label);
        panel.add(textField);
        panel.add(affirm);

        frame.setTitle("验证码");
        frame.add(panel);
        frame.setSize(400,300);
        frame.setVisible(true);
    }

    public static void main(String[] args)  {
        new CheckCode();
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
