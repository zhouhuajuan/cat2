package com.zhj.event.view;

import com.zhj.event.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCenters implements ActionListener {

    private JPanel panel = new JPanel();
    private static JFrame frame = new JFrame();
    private JLabel namelab = new JLabel("昵    称");
    private JLabel sexlab = new JLabel("性    别");
    private JLabel signaturelab = new JLabel("个性签名");
    private JLabel updatelab = new JLabel("修改密码");
    private JLabel namelab1 = new JLabel("用户名");
    private JLabel passlab = new JLabel("旧密码");
    private JLabel newpasslab = new JLabel("新密码");
    private JTextField nametext1 = new JTextField();
    private JPasswordField passtext = new JPasswordField();
    private JPasswordField newpasstext = new JPasswordField();
    private JTextField nametext = new JTextField();
    private JTextField signaturetext = new JTextField();
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    UserController userController = new UserController();
    String text;

    public JRadioButton man = new JRadioButton("男");
    public JRadioButton woman = new JRadioButton("女");
    public JButton ok = new JButton("保存");
    public JButton revise = new JButton("修改");
    public JButton back = new JButton("返回主页");

    public MyCenters(){
        Font font = new Font("宋体", Font.BOLD, 14);
        frame.setTitle("个人中心");
        panel.setLayout(null);
        namelab.setBounds(80,70,100,30);
        nametext.setBounds(180,70,200,30);
        sexlab.setBounds(80,120,100,30);
        man.setBounds(180,120,60,30);
        woman.setBounds(250,120,60,30);
        signaturelab.setBounds(80,170,100,30);
        signaturetext.setBounds(180,170,200,30);
        ok.setBounds(420,120,90,30);
        updatelab.setBounds(80,220,100,30);
        namelab1.setBounds(170,230,100,30);
        nametext1.setBounds(240,230,150,30);
        passlab.setBounds(170,280,100,30);
        passtext.setBounds(240,280,150,30);
        newpasslab.setBounds(170,330,100,30);
        newpasstext.setBounds(240,330,150,30);
        revise.setBounds(420,270,90,30);
        back.setBounds(250,380,100,30);

        panel.add(namelab);
        panel.add(nametext);
        panel.add(sexlab);
        panel.add(man);
        panel.add(woman);
        panel.add(signaturelab);
        panel.add(signaturetext);
        panel.add(ok);
        panel.add(updatelab);
        panel.add(namelab1);
        panel.add(nametext1);
        panel.add(passlab);
        panel.add(passtext);
        panel.add(newpasslab);
        panel.add(newpasstext);
        panel.add(revise);
        panel.add(back);

        buttonGroup1.add(man);
        buttonGroup2.add(woman);

        namelab.setFont(font);
        sexlab.setFont(font);
        signaturelab.setFont(font);
        man.setFont(font);
        woman.setFont(font);
        ok.setFont(font);
        updatelab.setFont(font);
        namelab1.setFont(font);
        passlab.setFont(font);
        newpasslab.setFont(font);
        revise.setFont(font);
        back.setFont(font);

        man.addActionListener(this);
        woman.addActionListener(this);
        ok.addActionListener(this);
        revise.addActionListener(this);
        back.addActionListener(this);

        frame.add(panel);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyCenters();
    }

    public static void closeThis() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok){
            ok();
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

    public void ok(){

    }

    public void revise() {
        Boolean result = userController.revise(nametext1.getText(),passtext.getText(),newpasstext.getText());
        if(result){
            JOptionPane.showMessageDialog(null, "密码修改成功！");
        }else {
            JOptionPane.showMessageDialog(null, "用户不存在！");
        }
    }
}
