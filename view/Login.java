package com.zhj.event.view;

import com.zhj.event.controller.ManagerController;
import com.zhj.event.controller.UserController;
import com.zhj.event.view.Register;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author 大娟
 * @ClassName Login.java
 * @Description 登录类
 * @Param
 * @createTime 2020年3月28日16:19
 */

public class Login extends JFrame implements ActionListener {
    private JPanel pan = new JPanel();  //创建JPanel面板对象
    private static JFrame jf = new JFrame();
    private JLabel label = new JLabel("英雄联盟");
    private JLabel namelab = new JLabel("账    号");  //创建带文本“用户名”的标签
    private JLabel passlab = new JLabel("密    码");  //创建带文本“密    码”的标签
    private JTextField nametext = new JTextField();  //创建文本框（JTextField）对象
    private JPasswordField passtext = new JPasswordField();  //创建密码框（JPasswordField）对象
    private ButtonGroup bg1 = new ButtonGroup();
    private ButtonGroup bg2 = new ButtonGroup();
    UserController userController = new UserController();
    ManagerController managerController = new ManagerController();

    String jrtext;

    //创建带文本的登录、注册和单选按钮
    private JButton login = new JButton("登录");
    public JButton register = new JButton("注册");
    public JRadioButton jr1 = new JRadioButton("用户");
    public JRadioButton jr2 = new JRadioButton("管理员");

    //定义无参构造方法
    public Login() {
        Font font = new Font("宋体", Font.BOLD, 12);  //创建Font对象，并初始化font的字体名，风格和大小
        Font font1 = new Font("宋体", Font.BOLD, 16);
        jf.setTitle("欢迎登录英雄联盟赛事票务管理系统");
        pan.setLayout(null);  //使该窗体（or面板）取消布局管理器设置
        label.setBounds(150,25,90,20);
        namelab.setBounds(70, 60, 60, 30);
        nametext.setBounds(140, 60, 140, 30);
        passlab.setBounds(70, 100, 60, 30);
        passtext.setBounds(140, 100, 140, 30);
        jr1.setBounds(100, 140, 60, 30);
        jr2.setBounds(190, 140, 90, 30);
        login.setBounds(80, 190, 90, 20);
        register.setBounds(190, 190, 90, 20);


        pan.add(namelab);
        pan.add(nametext);
        pan.add(passlab);
        pan.add(passtext);
        pan.add(login);
        pan.add(register);
        pan.add(jr1);
        pan.add(jr2);
        pan.add(label);

        bg1.add(jr1);
        bg1.add(jr2);

        login.setFont(font);
        register.setFont(font);
        jr1.setFont(font);
        jr2.setFont(font);
        label.setFont(font1);

        login.addActionListener(this);
        register.addActionListener(this);
        jr1.addActionListener(this);
        jr2.addActionListener(this);

        jf.add(pan);//调用本类的方法
        jf.setSize(400, 300);
        jf.setVisible(true);

    }


    public static void main(String[] args) {
        new Login();  //实例化一个Login对象
    }

    public static void closeThis() {
        jf.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jr1){
            jr1();
        }else if(e.getSource() == jr2){
            jr2();
        } else if(e.getSource() == login){
            login();
        }else if(e.getSource() == register){
            register();
        }
    }

    public void jr1(){
        jrtext = jr1.getText();
    }

    public void jr2(){
        jrtext = jr2.getText();
    }

    public void login(){
        if(jrtext == "用户"){
           Boolean result = userController.login(nametext.getText(),passtext.getText());
            if(result){
                closeThis();
                new HomePage();
            }
        } else if (jrtext == "管理员") {
            Boolean result = managerController.login(nametext.getText(),passtext.getText());
            if(result){
                closeThis();
                new HomePage2();
            }
        }
    }

    public void register(){
        closeThis();
        new Register();
    }

}