package Gui.MenuSwing;

import Gui.CheckSwing.CheckSwing;
import Gui.DepartSwing.DepartSwing;
import Gui.InsertSwing.InsertSwing;
import Gui.LoginSwing.BackGroundPanel;
import Gui.LoginSwing.ScreenUtils;
import Gui.UpdateSwing.UpdateSwing;
import 各项功能.StuDAO;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * 菜单显示页面
 * 设置菜单布局以及各功能的按钮
 */
public class MenuSwing extends JFrame {
    //初始化
    public MenuSwing(){
       init();
        setVisible(true);
    }

    //菜单布局
    void init() {
        setLayout(null);//采用坐标布局
        //设置背景
        BackGroundPanel bgPanelM = null;
        try {
            bgPanelM = new BackGroundPanel(ImageIO.read(new File(ClassLoader.getSystemResource("images/v2-8299d5eae2d7c4ac66450baad1a1496a_r.jpg").toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bgPanelM.setBounds(0,0,850,500);

        //组装元素
        Box BigBox = Box.createVerticalBox();

        //组装标题
        Box TBox = Box.createHorizontalBox();
        TBox.add(Box.createHorizontalStrut(-10));
        JLabel null1 = new JLabel("   ");
        JLabel Titile = new JLabel("管理菜单");
        Titile.setFont(new Font("微软雅黑",Font.BOLD,18));
        TBox.add(null1);
        TBox.add(Titile);

        //制作信息总表显示组件
         StuDAO dao = new StuDAO();
        //从这里开始是二维数组的遍历使用
        String[] rowName = { "学号", "姓名", "性别" ,"籍贯", "政治面貌", "班级"};
       //使用嵌套的键值对保存数据
        Map<Integer,ArrayList<String>> list = dao.findAll();  //传回带回数据的集合
        Set<Integer> keys = list.keySet();
        int row = list.size();
        //定义二维数组
        Object[][] obj = new Object[row][rowName.length];
        int j = 0;
        //将键值对数据导入二维数组中
        for (Integer key : keys) {
            ArrayList<String> arr = list.get(key);
            for (int i = 0; i < arr.size(); i++) {
                obj[j][i]= arr.get(i);
            }
            j++;
        }
        //将集合的数据导入JTable中
        final JTable table = new JTable(obj,rowName);
        table.setRowHeight(20);

        //这一步不能省去，否则显示不出列名
        // 创建滚动条组件，默认滚动条始终出现，初始化列表组件
        JScrollPane JSP=new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        JSP.setBounds(20,10,1250,200);


        //组装查询和修改
        Box Box1 = Box.createHorizontalBox();
        JButton searchB = new JButton("查询详细");
        JButton updateB = new JButton("修改数据");
        Box1.add(searchB);
        Box1.add(Box.createHorizontalStrut(40));
        Box1.add(updateB);

        //增加删除
        Box Box2 = Box.createHorizontalBox();
        JButton insertB = new JButton("增加数据");
        JButton deleteB = new JButton("删除数据");
        Box2.add(insertB);
        Box2.add(Box.createHorizontalStrut(40));
        Box2.add(deleteB);

        //院级管理 查询专业 院级 班级人数 退出系统
        Box Box3 = Box.createHorizontalBox();
        JButton DepartB = new JButton("统计管理");
        JButton exitB = new JButton("退出系统");
        Box3.add(DepartB);
        Box3.add(Box.createHorizontalStrut(40));
        Box3.add(exitB);

        //组装
        BigBox.add(Box.createVerticalStrut(20));
        BigBox.add(TBox);
        BigBox.add(Box.createVerticalStrut(10));
        BigBox.add(Box1);
        BigBox.add(Box.createVerticalStrut(20));
        BigBox.add(Box2);
        BigBox.add(Box.createVerticalStrut(20));
        BigBox.add(Box3);

        bgPanelM.add(JSP);
        bgPanelM.add(BigBox);

        add(bgPanelM);
        pack();

        //查找监视器
        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();//选中第几行
                int index = 0;
                if(row==-1){
                    //未选中提示
                    JOptionPane.showMessageDialog(null,"您没有选中信息");
                }
               String id = (String) table.getValueAt(row,index);
                CheckSwing check = new CheckSwing(id);
                setVisible(false);
            }
        });

        //修改监视器
        updateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();//选中第几行
                int index = 0;
                if(row==-1){
                    //未选中提示
                    JOptionPane.showMessageDialog(null,"您没有选中信息");
                }
                String id = (String) table.getValueAt(row,index);
                UpdateSwing update = new UpdateSwing(id);
                setVisible(false);

            }
        });

        //添加数据
        //添加数据的格式也需要正确
        insertB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InsertSwing insert = new InsertSwing();
                setVisible(false);
            }
        });

        //删除数据，删除成功后刷新展示新菜单页面
        deleteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();//选中第几行
                int index = 0;
                if(row==-1){
                    //未选中提示
                    JOptionPane.showMessageDialog(null,"您没有选中信息");
                }
                String id = (String) table.getValueAt(row,index);
                dao.DMLDelete(id);
                setVisible(false);
                MenuSwing.main();
            }
        });

        //院系管理
        DepartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepartSwing depart = new DepartSwing();
                setVisible(false);
            }
        });

        //退出系统
        exitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"退出成功，欢迎下次登录");
                System.exit(0);

            }
        });
    }

    public static void main() {
        MenuSwing menu =  new MenuSwing();
        menu.setBounds((ScreenUtils.getScreenWidth()-850)/2,
                (ScreenUtils.getScreenHeight()-450)/2,850,500);
        menu.setResizable(true);
        menu.setTitle("管理系统");
    }
}