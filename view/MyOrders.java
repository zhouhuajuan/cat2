package com.zhj.event.view;

import com.zhj.event.controller.OrderController;
import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.OrderDaoImpl;
import com.zhj.event.dao.impl.UserDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MyOrders implements ActionListener {
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("你的订单列表空空如也，快去预定赛事吧！");
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JTable table = new JTable();
    private Vector columnNames = new Vector();
    OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    OrderController orderController = new OrderController();
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    UserController userController = new UserController();

    public JButton cancel = new JButton("取消");
    public JButton back = new JButton("返回主页");
    public static String name;
    public int userId;
    public int balance;

    public MyOrders(){
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体",Font.BOLD,16);
        frame.setTitle("我的订单");

        cancel.setFont(font);
        back.setFont(font);
        label.setFont(font1);

        cancel.addActionListener(this);
        back.addActionListener(this);

        columnNames.add(0,"gameId");
        columnNames.add(1,"date");
        columnNames.add(2,"host_team");
        columnNames.add(3,"guest_team");
        columnNames.add(4,"price");

        int result = orderController.getUserIdByName(name);
        System.out.println(result);
        if(result == 1){
            userId = orderDaoImpl.userId;
            System.out.println(userId);
            Boolean result1 = orderDaoImpl.queryOrder(userId);
            if (result1){
                DefaultTableModel model = new DefaultTableModel(orderDaoImpl.rowData,columnNames);
                table.setModel(model);
            }else{
                panel3.add(label);
                frame.add(panel3,BorderLayout.NORTH);
            }
        }
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
        panel.add(scrollPane);

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        cancel.setPreferredSize(new Dimension(80,25));
        panel1.add(cancel);
        panel1.add(back);

        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.NORTH);
        frame.add(panel1,BorderLayout.SOUTH);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyOrders();
    }

    public void closeThis() {
        frame.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            closeThis();
            new HomePage();
        }else if(e.getSource() == cancel){
            cancel();
        }
    }

    public void cancel(){
        //获取你选中的行号（记录）
        int count=table.getSelectedRow();
        //读取你获取行号的某一列的值（也就是字段）
        String gameId1= table.getValueAt(count, 0).toString();
        int gameId = Integer.parseInt(gameId1);
        String price1 = table.getValueAt(count,4).toString();
        int price = Integer.parseInt(price1);
        Boolean result = orderController.cancelOrder(userId,gameId);
        if(result){
            JOptionPane.showMessageDialog(null, "取消订单成功！");
            Boolean result1 = userController.getBalanceByUserId(userId);
            if(result1){
                balance = userDaoImpl.balance;
                int total = balance+price;
                userController.chargeMoney(userId,total);
            }
            closeThis();
            new MyOrders();
        }else {
            JOptionPane.showMessageDialog(null, "取消订单失败！");
        }
    }
}
