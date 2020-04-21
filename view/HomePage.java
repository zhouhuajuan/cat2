package com.zhj.event.view;

import com.zhj.event.controller.OrderController;
import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.dao.impl.OrderDaoImpl;
import com.zhj.event.dao.impl.UserDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * @program: cat
 * @description: 用户登陆的主页类
 * @author: 周华娟
 * @create: 2020-04-20 16:22
 **/

public class HomePage implements ActionListener {
    JFrame frame = new JFrame();
    JTable table ;
    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JLabel label = new JLabel("请输入要搜索的战队：");

    /**
     * 定义一个列名的Vector集合
     */
    Vector columnNames = new Vector();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    OrderController orderController = new OrderController();
    UserController userController = new UserController();

    JButton myCenter = new JButton("个人中心");
    JButton myOrders = new JButton("我的订单");
    JButton myWallet = new JButton("我的钱包");
    JTextField textField = new JTextField();
    JButton search = new JButton("搜索");
    JButton reserve = new JButton("预定");

    /**
     * 定义一个静态的String类型成员变量
     */
    static String name;

    /**
     * 定义全局变量userId和balance
     */
    int userId;
    int balance;

    public HomePage() {
        Font font = new Font("宋体", Font.BOLD, 12);

        //设置按钮的文本风格
        myCenter.setFont(font);
        myOrders.setFont(font);
        myWallet.setFont(font);
        search.setFont(font);
        reserve.setFont(font);

        //为按钮设置监听事件
        myCenter.addActionListener(this);
        myOrders.addActionListener(this);
        myWallet.addActionListener(this);
        reserve.addActionListener(this);

        //为搜索框添加鼠标监听事件
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeThis();
                new SearchGame();
            }
        });

        //设置panel使用流布局管理器，使组件左对齐，并添加组件
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(myCenter);
        panel.add(myOrders);
        panel.add(myWallet);

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        textField.setPreferredSize(new Dimension(250,30));
        panel1.add(label);
        panel1.add(textField);
        panel1.add(search);

        //为columnNames集合添加元素
        columnNames.add(0,"id");
        columnNames.add(1,"date");
        columnNames.add(2,"host_team");
        columnNames.add(3,"guest_team");
        columnNames.add(4,"price");

        //调用GameDaoImpl的queryAllGame()方法获得行数据的集合
        gameDaoImpl.queryAllGame();
        table = new JTable(gameDaoImpl.rowData,columnNames);
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
        //panel2取消布局管理器设置，并添加滚动面板和预定按钮
        panel2.setLayout(null);
        reserve.setBounds(250,335,100,30);
        scrollPane.setBounds(5,5,580,320);
        panel2.add(reserve);
        panel2.add(scrollPane);
        panel2.setBorder(new EmptyBorder(10, 10, 10, 10));

        //设置frame使用边界布局管理器
        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.SOUTH);
        frame.add(panel2,BorderLayout.CENTER);
        frame.add(panel1,BorderLayout.NORTH);
        frame.setTitle("英雄联盟职业联赛");
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
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
        if(e.getSource() == myCenter){
            MyCenters.name = name;
            closeThis();
            new MyCenters();
        }else if(e.getSource() == myOrders){
            MyOrders.name = name;
            closeThis();
            new MyOrders();
        }else if(e.getSource() == myWallet){
            MyWallet.name = name;
            closeThis();
            new MyWallet();
        }else if(e.getSource() == reserve){
            reserve();

        }
    }

    //点击预定按钮处理的事件
    public void reserve(){
        //获取你选中的行号（记录）
        int count = table.getSelectedRow();
        //读取你获取行号的某一列的值（也就是字段）
        String gameId1= table.getValueAt(count, 0).toString();
        //把gameId1强制转换为int类型
        int gameId = Integer.parseInt(gameId1);
        String date = table.getValueAt(count,1).toString();
        String hostTeam = table.getValueAt(count,2).toString();
        String guestTeam = table.getValueAt(count,3).toString();
        String price1 = table.getValueAt(count,4).toString();
        //把price1强制转换为int类型
        int price = Integer.parseInt(price1);

        //调用getUserIdByName()方法获得userId
        int result = orderController.getUserIdByName(name);
        if(result == 1) {
            userId = OrderDaoImpl.userId;
            //调用getBalanceByUserId()方法获得balance
            Boolean result1 = userController.getBalanceByUserId(userId);
            if(result1){
                balance =userDaoImpl.balance;
                if(balance>=price) {
                    Boolean result2 = orderController.reserve(userId, gameId, date, hostTeam, guestTeam, price);
                    if (result2) {
                        JOptionPane.showMessageDialog(null, "预定成功！");
                        int total = balance-price;
                        userController.deductMoney(userId,total);
                    } else {
                        JOptionPane.showMessageDialog(null, "该订单已存在！");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "余额不足，无法预定该赛事！");
                }
            }else {
                JOptionPane.showMessageDialog(null, "出故障啦！");
            }

        }else {
            JOptionPane.showMessageDialog(null, "预定失败！");
        }

    }
}
