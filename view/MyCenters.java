package com.zhj.event.view;

import com.zhj.event.controller.UserController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyCenters implements ActionListener {

    private JPanel pan = new JPanel();  //创建JPanel面板对象
    private static JFrame jf = new JFrame();
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
    private ButtonGroup bg1 = new ButtonGroup();
    private ButtonGroup bg2 = new ButtonGroup();
    UserController userController = new UserController();
    String jrtext;

    public JRadioButton jr1 = new JRadioButton("男");
    public JRadioButton jr2 = new JRadioButton("女");
    public JButton ok = new JButton("保存");
    public JButton update = new JButton("修改");
    public JButton back = new JButton("返回主页");

    public MyCenters(){
        Font font = new Font("宋体", Font.BOLD, 14);  //创建Font对象，并初始化font的字体名，风格和大小
        jf.setTitle("个人中心");
        pan.setLayout(null);  //使该窗体（or面板）取消布局管理器设置
        namelab.setBounds(80,70,100,30);
        nametext.setBounds(180,70,200,30);
        sexlab.setBounds(80,120,100,30);
        jr1.setBounds(180,120,60,30);
        jr2.setBounds(250,120,60,30);
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
        update.setBounds(420,270,90,30);
        back.setBounds(250,380,100,30);

        pan.add(namelab);
        pan.add(nametext);
        pan.add(sexlab);
        pan.add(jr1);
        pan.add(jr2);
        pan.add(signaturelab);
        pan.add(signaturetext);
        pan.add(ok);
        pan.add(updatelab);
        pan.add(namelab1);
        pan.add(nametext1);
        pan.add(passlab);
        pan.add(passtext);
        pan.add(newpasslab);
        pan.add(newpasstext);
        pan.add(update);
        pan.add(back);

        bg1.add(jr1);
        bg1.add(jr2);

        namelab.setFont(font);
        sexlab.setFont(font);
        signaturelab.setFont(font);
        jr1.setFont(font);
        jr2.setFont(font);
        ok.setFont(font);
        updatelab.setFont(font);
        namelab1.setFont(font);
        passlab.setFont(font);
        newpasslab.setFont(font);
        update.setFont(font);
        back.setFont(font);

        jr1.addActionListener(this);
        jr2.addActionListener(this);
        ok.addActionListener(this);
        update.addActionListener(this);
        back.addActionListener(this);

        jf.add(pan);
        jf.setSize(600, 500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new MyCenters();
    }

    public static void closeThis() {
        jf.dispose();
    }

    public void jr1(){
        jrtext = jr1.getText();
    }

    public void jr2(){
        jrtext = jr2.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok){
            ok();
        }else if(e.getSource() == jr1){
            jr1();
        }else if(e.getSource() == jr2){
            jr2();
        }else if(e.getSource() == update){
            update();
        }else if(e.getSource() == back){
            closeThis();
            new HomePage();
        }
    }

    public void ok(){

    }

    public void update() {
        Boolean result = userController.update(nametext1.getText(),passtext.getText(),newpasstext.getText());
        if(result){
            jf.setVisible(false);
        }
    }
}
