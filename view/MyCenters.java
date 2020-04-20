package com.zhj.event.view;

import com.zhj.event.controller.MessageController;
import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.MessageDaoImpl;
import com.zhj.event.util.MD5Util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCenters implements ActionListener {

    private JPanel panel = new JPanel();
    private JFrame frame = new JFrame();
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
    private JTextField nametext;
    private JTextField signaturetext;
    private ButtonGroup buttonGroup1 = new ButtonGroup();
    private ButtonGroup buttonGroup2 = new ButtonGroup();
    private JLabel label = new JLabel("快来完善你的个人资料吧，让别人更好地了解你！");
    UserController userController = new UserController();
    MessageDaoImpl messageDaoImpl = new MessageDaoImpl();
    MessageController messageController = new MessageController();
    String text;

    public JRadioButton man = new JRadioButton("男");
    public JRadioButton woman = new JRadioButton("女");
    public JButton ok = new JButton("保存");
    public JButton revise = new JButton("修改");
    public JButton back = new JButton("返回主页");
    public static String name;
    public int userId;
    public String name1;
    public String signature;


    public MyCenters() {
        int result = messageDaoImpl.getUserIdByName(name);
        System.out.println(result);
        if(result == 1) {
            userId = messageDaoImpl.userId;
            Boolean result1 = messageDaoImpl.getMessageByUserId(userId);
            if(result1){
                name1 = messageDaoImpl.name;
                signature = messageDaoImpl.signature;
            }
        }else {
            JOptionPane.showMessageDialog(null, "出故障啦！");
        }

        nametext = new JTextField(name1);
        signaturetext = new JTextField(signature);

        Font font = new Font("宋体", Font.BOLD, 14);
        frame.setTitle("个人中心");
        panel.setLayout(null);
        label.setBounds(150,25,300,30);
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

        panel.add(label);
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

    public void closeThis() {
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
        //int result = messageDaoImpl.getUserIdByName(name);
        //System.out.println(result);
        //if(result == 1){
            //userId = messageDaoImpl.userId;
            Boolean result2 =messageController.insertMessage(userId,nametext.getText(),text,signaturetext.getText());
            if(result2){
                JOptionPane.showMessageDialog(null, "保存成功！");
            }else{
                JOptionPane.showMessageDialog(null, "保存失败！");
            }
        //}else {
           //JOptionPane.showMessageDialog(null, "保存失败！");
       // }
    }

    public void revise() {
        //校验输入的密码是否为8~16数字字母组合
        String check = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{8,16}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(newpasstext.getText());
        if(!matcher.matches()) {
            JOptionPane.showMessageDialog(null, "新密码的输入格式不符合！");
        }else {
            Boolean result = userController.revise(nametext1.getText(), passtext.getText(), newpasstext.getText());
            if (result) {
                JOptionPane.showMessageDialog(null, "密码修改成功！");
            } else {
                JOptionPane.showMessageDialog(null, "用户不存在！");
            }
        }
    }
}
