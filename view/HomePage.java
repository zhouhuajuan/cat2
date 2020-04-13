package com.zhj.event.view;

import com.zhj.event.controller.GameController;
import com.zhj.event.dao.impl.GameDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage implements ActionListener {
    private static JFrame frame = new JFrame();
    private JTable table ;
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    GameController gameController = new GameController();

    public JButton myCenter = new JButton("个人中心");
    public JButton myOrders = new JButton("我的订单");
    public JButton myWallet = new JButton("我的钱包");
    String text = "请输入战队搜索赛事";
    private JTextField jTextField = new JTextField(text);
    private JButton search = new JButton("搜索");
    private JButton reserve = new JButton("预定");

    public HomePage() {
        Font font = new Font("宋体", Font.BOLD, 12);
        frame.setTitle("英雄联盟职业联赛");

        myCenter.setFont(font);
        myOrders.setFont(font);
        myWallet.setFont(font);
        search.setFont(font);

        myCenter.addActionListener(this);
        myOrders.addActionListener(this);
        myWallet.addActionListener(this);
        search.addActionListener(this);

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(myCenter);
        panel.add(myOrders);
        panel.add(myWallet);

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField.setPreferredSize(new Dimension(350,30));
        panel1.add(jTextField);
        panel1.add(search);

        gameDaoImpl.queryAllGame();
        table = new JTable(gameDaoImpl.rowData,gameDaoImpl.columnName);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);
        table.setFont(new Font(null, Font.PLAIN, 14));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);

        //定义一个滚动面板，并把表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel2.setLayout(null);
        reserve.setBounds(250,320,100,30);
        scrollPane.setBounds(5,5,580,350);
        panel2.add(reserve);
        panel2.add(scrollPane);
        panel2.setBorder(new EmptyBorder(10, 10, 10, 10));

        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.SOUTH);
        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel1,BorderLayout.NORTH);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }

    public static void closeThis() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == myCenter){
            closeThis();
            new MyCenters();
        }else if(e.getSource() == myOrders){
            closeThis();
            new MyOrders();
        }else if(e.getSource() == myWallet){
            closeThis();
            new MyWallet();
        }else if(e.getSource() == search){
            search();
        }else if(e.getSource() == reserve){
            reserve();
        }
    }

    public void search(){
       Boolean result = gameController.search(jTextField.getText());
       if(result){
           gameDaoImpl.queryAnyGame(jTextField.getText());
           table = new JTable(gameDaoImpl.rowData,gameDaoImpl.columnName);
           JScrollPane scrollPane = new JScrollPane(table);
           panel2.add(scrollPane);
           //刷新Table
           panel2.validate();
       }else {
           JOptionPane.showMessageDialog(null, "对不起，未找到相关内容！");
       }
    }

    public void reserve(){

    }
}
