package Gui.DepartSwing;

import Gui.LoginSwing.ScreenUtils;
import Gui.MenuSwing.MenuSwingStu;
import 各项功能.StuDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * 院系管理功能
 * 对现有的院系班级进行展示，以及查询选中班级的人数总数。
 */
public class DepartSwingStu extends JFrame{

    public DepartSwingStu(){
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);

        //调用各功能的方法studao
        StuDAO stu = new StuDAO();
        //从这里开始是二维数组的遍历使用
        String[] rowName = {"学院","院长","专业","班级"};
        //使用嵌套的键值对保存数据
        Map<Integer, ArrayList<String>> map = stu.FindAllDepart(); //传回带回数据的集合
        Set<Integer> keys = map.keySet();
        int row = map.size();
        //定义二维数组
        Object[][] obj = new Object[row][rowName.length];
        int j = 0;
        //将键值对数据导入二维数组中
        for (Integer key : keys) {
            ArrayList<String> arr = map.get(key);
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
        JSP.setBounds(20,10,350,200);
        add(JSP);

        //查询班级人数
        JButton insertB2 = new JButton("查询人数");
        insertB2.setBounds(50,250,100,30);
        insertB2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();//选中第几行
                int index = 3;
                if(row==-1){
                    JOptionPane.showMessageDialog(null,"您没有选中信息");
                }
                String id = (String) table.getValueAt(row,index);
                int a = stu.FindNumber(id);
                JOptionPane.showMessageDialog(null,"班级一共有："+ a +"人");
            }
        });
        add(insertB2);

        //设置返回键
        JButton returnB = new JButton("返回");
        returnB.setBounds(200,250,80,30);
        returnB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuSwingStu return4 =new MenuSwingStu();
                return4.setBounds((ScreenUtils.getScreenWidth()-450)/2,
                        (ScreenUtils.getScreenHeight()-350)/2,400,300);
                return4.setResizable(true);
                return4.setTitle("管理系统");
                setVisible(false);
            }
        });
        add(returnB);
        setVisible(true);
    }

}
