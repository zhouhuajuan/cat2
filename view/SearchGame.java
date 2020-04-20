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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGame implements ActionListener {
    private JFrame frame = new JFrame();
    private JLabel label = new JLabel("请输入要搜索的战队：");
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JTable table = new JTable();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    GameController gameController = new GameController();
    OrderDaoImpl orderDapImpl = new OrderDaoImpl();
    OrderController orderController = new OrderController();
    UserDaoImpl userDaoImpl = new UserDaoImpl();
    UserController userController = new UserController();

    private JTextField jTextField = new JTextField();
    public JButton search = new JButton("搜索");
    public JButton back = new JButton("返回主页");
    public JButton reserve = new JButton("预定");
    public static String name;
    public int balance;

    public SearchGame(){
        Font font = new Font("宋体", Font.BOLD, 12);
        frame.setTitle("英雄联盟职业联赛");

        search.setFont(font);
        back.setFont(font);

        search.addActionListener(this);
        back.addActionListener(this);
        reserve.addActionListener(this);

        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField.setPreferredSize(new Dimension(250,30));
        panel.add(label);
        panel.add(jTextField);
        panel.add(search);

        panel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        reserve.setPreferredSize(new Dimension(90,25));
        panel2.add(reserve);
        panel2.add(back);

        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.NORTH);
        frame.add(panel2,BorderLayout.SOUTH);
        frame.setSize(600, 500);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search) {
            search();
        }else if(e.getSource() == back){
            closeThis();
            new HomePage();
        }else if(e.getSource() == reserve){
            reserve();
        }
    }

    public void search(){
        Boolean result = gameController.search(jTextField.getText());
        if (result) {
            gameDaoImpl.queryAnyGame(jTextField.getText());
            DefaultTableModel model = new DefaultTableModel(gameDaoImpl.rowData, gameDaoImpl.columnName);
            table.setModel(model);
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
            panel1.add(scrollPane);
            panel1.setBorder(new EmptyBorder(10, 10, 10, 10));

            frame.add(panel1, BorderLayout.CENTER);
            //刷新面板
            panel1.revalidate();
            System.out.println("world");
        }else {
            JOptionPane.showMessageDialog(null, "对不起，未找到相关内容！");
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
        System.out.println(gameId);
        System.out.println(name);
        int result = orderController.getUserIdByName(name);
        if(result == 1) {
            int userId = OrderDaoImpl.userId;
            Boolean result1 = userController.getBalanceByUserId(userId);
            if(result1){
                balance = userDaoImpl.balance;
                if(balance>=price){
                    Boolean result2 = orderController.reserve(userId, gameId,date,hostTeam,guestTeam,price);
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

    public static void main(String[] args) {
        new SearchGame();
    }

    public void closeThis() {
        frame.dispose();
    }
}
