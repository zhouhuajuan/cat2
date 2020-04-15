package com.zhj.event.view;

import com.zhj.event.controller.GameController;
import com.zhj.event.dao.impl.GameDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchGame implements ActionListener {
    private static JFrame frame = new JFrame();
    private JLabel label = new JLabel("请输入要搜索的战队：");
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JTable table;
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    GameController gameController = new GameController();

    private JTextField jTextField = new JTextField();
    public JButton search = new JButton("搜索");
    public JButton back = new JButton("返回主页");
    public JButton reserve = new JButton("预定");

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

        frame.setLayout(new BorderLayout());
        frame.add(panel,BorderLayout.NORTH);
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
            //刷新Table
           // panel1.validate();
            //panel1.repaint();

            gameDaoImpl.queryAnyGame(jTextField.getText());
            table = new JTable(gameDaoImpl.rowData, gameDaoImpl.columnName);
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
            //刷新Table
            //panel1.validate();

            frame.add(panel1, BorderLayout.CENTER);
            System.out.println("world");
        }else {
            JOptionPane.showMessageDialog(null, "对不起，未找到相关内容！");
        }
    }

    public void reserve(){

    }

    public static void main(String[] args) {
        new SearchGame();
    }

    public static void closeThis() {
        frame.dispose();
    }
}
