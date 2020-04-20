package com.zhj.event.view;

import com.zhj.event.controller.GameController;
import com.zhj.event.controller.OrderController;
import com.zhj.event.controller.UserController;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.dao.impl.OrderDaoImpl;
import com.zhj.event.dao.impl.UserDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class HomePage implements ActionListener {
    private JFrame frame = new JFrame();
    private JTable table ;
    private DefaultTableModel update_table;
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel label = new JLabel("请输入要搜索的战队：");
    private Vector columnNames = new Vector();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    OrderDaoImpl orderDapImpl = new OrderDaoImpl();
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    GameController gameController = new GameController();
    OrderController orderController = new OrderController();
    UserController userController = new UserController();

    public JButton myCenter = new JButton("个人中心");
    public JButton myOrders = new JButton("我的订单");
    public JButton myWallet = new JButton("我的钱包");
    private JTextField jTextField = new JTextField();
    public JButton search = new JButton("搜索");
    public JButton reserve = new JButton("预定");
    public static String name;
    public static int userId;
    public int balance;

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
        reserve.addActionListener(this);
        jTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                closeThis();
                new SearchGame();
            }
        });

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(myCenter);
        panel.add(myOrders);
        panel.add(myWallet);

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField.setPreferredSize(new Dimension(250,30));
        panel1.add(label);
        panel1.add(jTextField);
        panel1.add(search);

        columnNames.add(0,"id");
        columnNames.add(1,"date");
        columnNames.add(2,"host_team");
        columnNames.add(3,"guest_team");
        columnNames.add(4,"price");

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
        panel2.setLayout(null);
        reserve.setBounds(250,335,100,30);
        scrollPane.setBounds(5,5,580,320);
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

    public void closeThis() {
        frame.dispose();
    }


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

    public void reserve(){
        //获取你选中的行号（记录）
        int count=table.getSelectedRow();
        //读取你获取行号的某一列的值（也就是字段）
        String gameId1= table.getValueAt(count, 0).toString();
        int gameId = Integer.parseInt(gameId1);
        String date = table.getValueAt(count,1).toString();
        String hostTeam = table.getValueAt(count,2).toString();
        String guestTeam = table.getValueAt(count,3).toString();
        String price1 = table.getValueAt(count,4).toString();
        int price = Integer.parseInt(price1);
        int result = orderController.getUserIdByName(name);
        if(result == 1) {
            userId = OrderDaoImpl.userId;
            Boolean result1 = userController.getBalanceByUserId(userId);
            if(result1){
                balance =userDaoImpl.balance;
                if(balance>=price) {
                    Boolean result2 = orderController.reserve(userId, gameId, date, hostTeam, guestTeam, price);
                    if (result2 == true) {
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
