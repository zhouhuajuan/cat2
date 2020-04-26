package com.zhj.event.view;

import com.zhj.event.controller.MessageController;
import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.MessageDaoImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: cat
 * @description: 个人中心类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class MyCenters implements ActionListener {

    JPanel panel = new JPanel();
    JFrame frame = new JFrame();
    JLabel nameLab = new JLabel("昵    称");
    JLabel sexLab = new JLabel("性    别");
    JLabel signatureLab = new JLabel("个性签名");
    JLabel updateLab = new JLabel("修改密码");
    JLabel nameLab1 = new JLabel("用户名");
    JLabel passLab = new JLabel("旧密码");
    JLabel newPassLab = new JLabel("新密码");
    JTextField nameText1 = new JTextField();
    JPasswordField passText = new JPasswordField();
    JPasswordField newPassText = new JPasswordField();
    JTextField nameText;
    JTextField signatureText;
    ButtonGroup buttonGroup1 = new ButtonGroup();
    ButtonGroup buttonGroup2 = new ButtonGroup();
    JLabel label = new JLabel("快来完善你的个人资料吧，让别人更好地了解你！");

    UserController userController = new UserController();
    MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
    MessageController messageController = new MessageController();

    JRadioButton man = new JRadioButton("男");
    JRadioButton woman = new JRadioButton("女");
    JButton affirm = new JButton("保存");
    JButton revise = new JButton("修改");
    JButton back = new JButton("返回主页");

    /**
     * 定义一个静态的String类型成员变量name
     */
    static String name;
    String text;
    int userId;
    String name1;
    String signature;


    public MyCenters() {
        //调用getUserIdByName获得userId
        int result = messageDaoImpl.getUserIdByName(name);
        if(result == 1) {
            userId = MessageDaoImpl.userId;
            boolean result1 = messageDaoImpl.getMessageByUserId(userId);
            if(result1){
                //获得昵称和个性签名
                name1 = MessageDaoImpl.name;
                signature = MessageDaoImpl.signature;
            }
        }else {
            JOptionPane.showMessageDialog(null, "出故障啦！");
        }

        //将获得昵称和个性签名并显示在文本框里
        nameText = new JTextField(name1);
        signatureText = new JTextField(signature);

        Font font = new Font("宋体", Font.BOLD, 14);

        //panel取消布局管理器设置
        panel.setLayout(null);
        label.setBounds(150,25,300,30);
        nameLab.setBounds(80,70,100,30);
        nameText.setBounds(180,70,200,30);
        sexLab.setBounds(80,120,100,30);
        man.setBounds(180,120,60,30);
        woman.setBounds(250,120,60,30);
        signatureLab.setBounds(80,170,100,30);
        signatureText.setBounds(180,170,200,30);
        affirm.setBounds(420,120,90,30);
        updateLab.setBounds(80,220,100,30);
        nameLab1.setBounds(170,230,100,30);
        nameText1.setBounds(240,230,150,30);
        passLab.setBounds(170,280,100,30);
        passText.setBounds(240,280,150,30);
        newPassLab.setBounds(170,330,100,30);
        newPassText.setBounds(240,330,150,30);
        revise.setBounds(420,270,90,30);
        back.setBounds(250,380,100,30);

        //为panel添加组件
        panel.add(label);
        panel.add(nameLab);
        panel.add(nameText);
        panel.add(sexLab);
        panel.add(man);
        panel.add(woman);
        panel.add(signatureLab);
        panel.add(signatureText);
        panel.add(affirm);
        panel.add(updateLab);
        panel.add(nameLab1);
        panel.add(nameText1);
        panel.add(passLab);
        panel.add(passText);
        panel.add(newPassLab);
        panel.add(newPassText);
        panel.add(revise);
        panel.add(back);

        //将男和女这两个单选按钮分组
        buttonGroup1.add(man);
        buttonGroup2.add(woman);

        //设置标签和按钮的文本风格
        nameLab.setFont(font);
        sexLab.setFont(font);
        signatureLab.setFont(font);
        man.setFont(font);
        woman.setFont(font);
        affirm.setFont(font);
        updateLab.setFont(font);
        nameLab1.setFont(font);
        passLab.setFont(font);
        newPassLab.setFont(font);
        revise.setFont(font);
        back.setFont(font);

        //为按钮添加监听事件
        man.addActionListener(this);
        woman.addActionListener(this);
        affirm.addActionListener(this);
        revise.addActionListener(this);
        back.addActionListener(this);

        frame.add(panel);
        frame.setTitle("个人中心");
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyCenters();
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
        if(e.getSource() == affirm){
            affirm();
        }else if(e.getSource() == man){
            text = man.getText();
        }else if(e.getSource() == woman){
            text = woman.getText();
        }else if(e.getSource() == revise){
            revise();
        }else if(e.getSource() == back){
            closeThis();
            new HomePage();
        }
    }

    //点击保存按钮的处理事件
    public void affirm(){
        boolean result2 =messageController.insertMessage(userId,nameText.getText(),text,signatureText.getText());
        if(result2){
            JOptionPane.showMessageDialog(null, "保存成功！");
        }else{
            JOptionPane.showMessageDialog(null, "保存失败！");
        }
    }

    //点击修改按钮的处理事件
    public void revise() {
        //校验输入的密码是否为8~16数字字母组合
        String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(newPassText.getText());
        if(!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "新密码的输入格式不符合！");
        }else {
            boolean result = userController.revise(nameText1.getText(), passText.getText(), newPassText.getText());
            if (result) {
                JOptionPane.showMessageDialog(null, "密码修改成功！");
            } else {
                JOptionPane.showMessageDialog(null, "用户不存在！");
            }
        }
    }
}
