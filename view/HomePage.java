package com.zhj.event.view;

import com.zhj.event.bean.ReserveGameParam;
import com.zhj.event.controller.GameController;
import com.zhj.event.controller.OrderController;
import com.zhj.event.dao.impl.GameDaoImpl;
import com.zhj.event.dao.impl.OrderDaoImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.Vector;

public class HomePage implements ActionListener {
    private static JFrame frame = new JFrame();
    private JTable table ;
    private DefaultTableModel update_table;
    private JPanel panel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JLabel label = new JLabel("请输入要搜索的战队：");
    private Vector columnNames = new Vector();
    GameDaoImpl gameDaoImpl = new GameDaoImpl();
    OrderDaoImpl orderDapImpl = new OrderDaoImpl();
    GameController gameController = new GameController();
    OrderController orderController = new OrderController();

    public JButton myCenter = new JButton("个人中心");
    public JButton myOrders = new JButton("我的订单");
    public JButton myWallet = new JButton("我的钱包");
    private JTextField jTextField = new JTextField();
    public JButton search = new JButton("搜索");
    public JButton reserve = new JButton("预定");
    public String name = Login.name;

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
        columnNames.add(5,"reserve");

        gameDaoImpl.queryAllGame();
        table = new JTable(gameDaoImpl.rowData,columnNames);
        table.setBorder(new LineBorder(new Color(0, 0, 0)));
        // 设置表格内容颜色
        table.setForeground(Color.BLACK);
        table.setFont(new Font(null, Font.PLAIN, 14));
        table.setSelectionForeground(Color.DARK_GRAY);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setGridColor(Color.GRAY);

        table.getColumnModel().getColumn(5).setCellEditor(new MyRender());//设置编辑器
        table.getColumnModel().getColumn(5).setCellRenderer(new MyRender() );

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
           //刷新Table
           panel2.validate();
       }else {
           JOptionPane.showMessageDialog(null, "对不起，未找到相关内容！");
       }
    }

    public void reserve(){
        int gameId = table.getSelectedRow();
        orderController.getUserIdByName(name);
        int userId = OrderDaoImpl.userId;
        Boolean result1 = orderController.reserve(userId,gameId);
        if(result1 == true){
            JOptionPane.showMessageDialog(null, "预定成功！");
        }else {
            JOptionPane.showMessageDialog(null, "该订单已存在！");
        }

    }
}

//渲染 器 编辑器
class MyRender extends AbstractCellEditor implements TableCellRenderer,ActionListener, TableCellEditor {

    OrderController orderController = new OrderController();
    private static final long serialVersionUID = 1L;
    private JButton button =null;
    private Object ReserveGameParam;

    public MyRender(){
        button = new JButton("预定");
        button.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //orderController.reserve(ReserveGameParam,param);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
}
