package Gui.InsertSwing;

import Gui.LoginSwing.ScreenUtils;
import Gui.MenuSwing.MenuSwing;
import 各项功能.StuDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

/**
 * 插入函数
 * 插入新的数据，插入成功后将刷新菜单，生成插入后的信息总表在菜单
 */
public class InsertSwing extends JFrame {
    public InsertSwing() {
        //设置格式
        setSize(300, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);

        //设置和组装组件
        JLabel j0 = new JLabel("添加信息");
        j0.setBounds(100, 20, 80, 30);
        add(j0);

        JLabel j1 = new JLabel("学号：");
        j1.setBounds(30, 70, 50, 30);
        add(j1);

        final JTextField jt1 = new JTextField();
        jt1.setBounds(100, 70, 130, 30);
        add(jt1);

        JLabel j2 = new JLabel("姓名：");
        j2.setBounds(30, 120, 50, 30);
        add(j2);

        final JTextField jt2 = new JTextField();
        jt2.setBounds(100, 120, 130, 30);
        add(jt2);

        JLabel j3 = new JLabel("性别：");
        j3.setBounds(30, 170, 50, 30);
        add(j3);

        final JTextField jt3 = new JTextField();
        jt3.setBounds(100, 170, 130, 30);
        add(jt3);

        JLabel j4 = new JLabel("籍贯：");
        j4.setBounds(30, 220, 50, 30);
        add(j4);

        final JTextField jt4 = new JTextField();
        jt4.setBounds(100, 220, 130, 30);
        add(jt4);

        JLabel j5 = new JLabel("政治面貌：");
        j5.setBounds(30, 270, 70, 30);
        add(j5);
        final JTextField jt5 = new JTextField();
        jt5.setBounds(100, 270, 130, 30);
        add(jt5);

        StuDAO stu1 = new StuDAO();

        //设置下拉列表
        JLabel j6 = new JLabel("学院：");
        j6.setBounds(30, 320, 50, 30);
        add(j6);
        JComboBox<String> jc1 = new JComboBox<>();
        //获取当前的学院名
        String xueyuanm = "学院名";
        ArrayList<String> xueyuan = stu1.findDepartXueyuan(xueyuanm);
        for (int i = 0; i < xueyuan.size(); i++) {
            String a = xueyuan.get(i);
            jc1.addItem(a);
        }
        jc1.setBounds(100, 320, 130, 30);
        add(jc1);

        JLabel j7 = new JLabel("班级：");
        j7.setBounds(30,370,50,30);
        add(j7);
        JComboBox<String> jc2 = new JComboBox<>();
        //获取当前选择的学院名下的班级名称
        jc1.addItemListener(new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            //获取用户选择的学院名
            jc1.removeAll();
            String classm = String.valueOf(jc1.getSelectedItem());
            ArrayList<String> className = stu1.findDepartClass(classm);
                for (int i = 0; i < className.size(); i++) {
                    String a = className.get(i);
                    jc2.addItem(a);
                   }
            }
        });
        jc2.setBounds(100, 370, 130, 30);
        add(jc2);


        JButton jb1 = new JButton("返回");
        jb1.setBounds(50, 430, 80, 30);
        add(jb1);

        JButton jb2 = new JButton("确认");
        jb2.setBounds(150, 430, 80, 30);
        add(jb2);
        setVisible(true);

        //返回键功能
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuSwing return1 =new MenuSwing();
                return1.setBounds((ScreenUtils.getScreenWidth()-850)/2,
                        (ScreenUtils.getScreenHeight()-450)/2,850,500);
                return1.setResizable(true);
                return1.setTitle("管理系统");
                setVisible(false);
            }
        });

        //确认键功能
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = jt1.getText().trim();
                String name = jt2.getText().trim();
                String sex = jt3.getText().trim();
                String country = jt4.getText().trim();
                String zzFace = jt5.getText().trim();
                String classs = String.valueOf(jc2.getSelectedItem());
                stu1.DMLInsert(id,name,sex,country,zzFace,classs);
            }
        });
    }
}
