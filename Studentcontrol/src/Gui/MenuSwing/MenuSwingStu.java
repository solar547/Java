package Gui.MenuSwing;

import Gui.CheckSwing.CheckSwingStu;
import Gui.DepartSwing.DepartSwingStu;
import Gui.LoginSwing.BackGroundPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 学生账户登录界面
 */
public class MenuSwingStu extends JFrame {

    public MenuSwingStu(){
        init();
        setVisible(true);
    }

    void init(){
        setLayout(null);//采用坐标布局
        //设置背景
        BackGroundPanel bgPanelM = null;
        try {
            bgPanelM = new BackGroundPanel(ImageIO.read(new File(ClassLoader.getSystemResource("images/v2-8299d5eae2d7c4ac66450baad1a1496a_r.jpg").toURI())));
        } catch (Exception e) {
            e.printStackTrace();
        }
        bgPanelM.setBounds(0,0,400,300);

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

        //组装文本框
        Box box = Box.createHorizontalBox();
        JLabel jl = new JLabel("请输入查找学号:");
        JTextField jf = new JTextField();
        box.add(jl);
        box.add(Box.createHorizontalStrut(20));
        box.add(jf);

        //组装查询和统计管理
        Box Box1 = Box.createHorizontalBox();
        JButton searchB = new JButton("查询详细");
        JButton DepartB = new JButton("统计管理");
        Box1.add(searchB);
        Box1.add(Box.createHorizontalStrut(40));
        Box1.add(DepartB);

        //组装
        BigBox.add(Box.createVerticalStrut(30));
        BigBox.add(TBox);
        BigBox.add(Box.createVerticalStrut(30));
        BigBox.add(box);
        BigBox.add(Box.createVerticalStrut(70));
        BigBox.add(Box1);

        bgPanelM.add(BigBox);
        add(bgPanelM);
        pack();


        //搜索查询
        searchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = jf.getText().trim();
                //查找记录
                CheckSwingStu check = new CheckSwingStu(id);
                setVisible(false);
            }
        });

        //院系管理
        DepartB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepartSwingStu depart = new DepartSwingStu();
                setVisible(false);
            }
        });
    }
}
