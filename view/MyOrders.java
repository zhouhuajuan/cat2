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

/**
 * @program: cat
 * @description: 我的订单类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class MyOrders implements ActionListener {
    JFrame frame = new JFrame();
    JLabel label = new JLabel("你的订单列表空空如也，快去预定赛事吧！");
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel3 = new JPanel();
    JTable table = new JTable();
    Vector columnNames = new Vector();

    OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    OrderController orderController = new OrderController();
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    UserController userController = new UserController();

    JButton cancel = new JButton("取消");
    JButton back = new JButton("返回主页");

    /**
     * 定义一个静态的String类型成员变量
     */
    static String name;


    /**
     * 定义全局变量userId和balance
     */
    int userId;
    int balance;

    public MyOrders(){
        Font font = new Font("宋体", Font.BOLD, 12);
        Font font1 = new Font("宋体",Font.BOLD,16);

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

        //调用getUserIdByName()方法获得userId
        int result = orderController.getUserIdByName(name);
        if(result == 1){
            userId = orderDaoImpl.userId;
            //调用queryOrder()方法获得行数据的集合并填充到表格里
            Boolean result1 = orderDaoImpl.queryOrder(userId);
            if (result1){
                DefaultTableModel model = new DefaultTableModel(orderDaoImpl.rowData,columnNames);
                table.setModel(model);
            }else{
                panel3.add(label);
                frame.add(panel3,BorderLayout.NORTH);
            }
        }

        // 设置表格内容颜色
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        table.setForeground(Color.BLACK);
        table.setFont(new Font(null, Font.PLAIN, 14));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);

        //定义一个滚动面板，并把表格添加到滚动面板中
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane);

        //设置panel1使用流布局管理器，使组件中间对齐
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        cancel.setPreferredSize(new Dimension(80,25));
        panel1.add(cancel);
        panel1.add(back);

        //设置frame使用边界布局管理器
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.NORTH);
        frame.add(panel1,BorderLayout.SOUTH);
        frame.setTitle("我的订单");
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new MyOrders();
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
        if(e.getSource() == back){
            closeThis();
            new HomePage();
        }else if(e.getSource() == cancel){
            cancel();
        }
    }

    //点击取消按钮的处理事件
    public void cancel(){
        //获取你选中的行号（记录）
        int count=table.getSelectedRow();

        //读取你获取行号的某一列的值（也就是字段）
        String gameId1= table.getValueAt(count, 0).toString();
        //将gameId1强制转化为int型
        int gameId = Integer.parseInt(gameId1);

        String price1 = table.getValueAt(count,4).toString();
        //将price1强制转化为int型
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
