package com.zhj.event.view;

import com.zhj.event.controller.GameController;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.entity.Game;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class HomePage<gameDaoImpl> implements ActionListener {
    private static JFrame jf = new JFrame();
    private JTable table = new JTable();
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private String head[]=null;
    private Object [][]data=null;
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    GameController gameController = new GameController();

    public JButton jb = new JButton("个人中心");
    public JButton jb1 = new JButton("我的订单");
    public JButton jb2 = new JButton("我的钱包");
    String text = "请输入战队搜索赛事";
    private JTextField jTextField = new JTextField(text);
    private JButton search = new JButton("搜索");

    public HomePage() {
        Font font = new Font("宋体", Font.BOLD, 12);  //创建Font对象，并初始化font的字体名，风格和大小
        Font font1 = new Font("宋体", Font.BOLD, 16);
        jf.setTitle("英雄联盟职业联赛");

        jb.setFont(font);
        jb1.setFont(font);
        jb2.setFont(font);
        search.setFont(font);

        jb.addActionListener(this);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        search.addActionListener(this);

        //设置知面板的边界，Border描述了面板四周的边界（属于面板内部），EmptyBorder是一个空白的边界；
        //语句的意思是让contentPane内部边框为空，并且有5个像素的厚度
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT)); // 设置面板的布局方式
        panel.add(jb);
        panel.add(jb1);
        panel.add(jb2);

        panel1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField.setPreferredSize(new Dimension(350,30));
        panel1.add(jTextField);
        panel1.add(search);

        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        head=new String[] {"日期", "对阵","是否预约"};

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.GRAY);                     // 网格颜色

        DefaultTableModel tableModel=new DefaultTableModel(queryData(),head){
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        };
        table.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        //scrollPane.setViewportView(table);
        jf.setLayout(new BorderLayout());
        jf.add(panel,BorderLayout.SOUTH);
        jf.add(scrollPane,BorderLayout.CENTER);
        jf.add(panel1,BorderLayout.NORTH);
        jf.setSize(600, 500);
        jf.setVisible(true);
    }

    public static void main(String[] args) {
        new HomePage();
    }

    public static void closeThis() {
        jf.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jb){
            closeThis();
            new MyCenters();
        }else if(e.getSource() == jb1){
            closeThis();
            new MyOrders();
        }else if(e.getSource() == jb2){
            closeThis();
            new MyWallet();
        }else if(e.getSource() == search){
            search();
        }
    }

    public Object[][] queryData(){
        List<Game> list=gameDaoImpl.queryAllGame();
        data=new Object[list.size()][head.length];

        for(int i=0;i<list.size();i++){
            for(int j=0;j<head.length;j++){
                data[i][0]=list.get(i).getDate();
                data[i][1]=list.get(i).getAgainst();
            }
        }
        return data;
    }

    public void search(){

    }
}
