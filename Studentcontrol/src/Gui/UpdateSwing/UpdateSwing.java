package Gui.UpdateSwing;

import Gui.LoginSwing.ScreenUtils;
import Gui.MenuSwing.MenuSwing;
import 各项功能.StuDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 修改功能
 * 修改选中的学号，可以修改学生的姓名，性别，籍贯，政治面貌以及班级
 * 注意：由于数据库建表的设置，班级必须是已经存在在数据库depart表中的班级，否则会报错修改失败
 */
public class UpdateSwing extends JFrame {
    public UpdateSwing(String id) {
        setSize(300,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(null);

        //设置页面布局
        JLabel j0=new JLabel("修改信息");
        j0.setBounds(100,20,80,30);
        add(j0);

        JLabel j1=new JLabel("学号：");
        j1.setBounds(30,70,50,30);
        add(j1);

        final JLabel jt1=new JLabel();
        jt1.setBounds(100,70,130,30);
        add(jt1);

        JLabel j2=new JLabel("姓名：");
        j2.setBounds(30,120,50,30);
        add(j2);

        final JTextField jt2=new JTextField();
        jt2.setBounds(100,120,130,30);
        add(jt2);

        JLabel j3=new JLabel("性别：");
        j3.setBounds(30,170,50,30);
        add(j3);

        final JTextField jt3=new JTextField();
        jt3.setBounds(100,170,130,30);
        add(jt3);

        JLabel j4=new JLabel("籍贯：");
        j4.setBounds(30,220,50,30);
        add(j4);

        final JTextField jt4=new JTextField();
        jt4.setBounds(100,220,130,30);
        add(jt4);

        JLabel j5=new JLabel("政治面貌：");
        j5.setBounds(30,270,70,30);
        add(j5);
        final JTextField jt5=new JTextField();
        jt5.setBounds(100,270,130,30);
        add(jt5);

        JLabel j6=new JLabel("班级：");
        j6.setBounds(30,320,50,30);
        add(j6);
        final JTextField jt6=new JTextField();
        jt6.setBounds(100,320,130,30);
        add(jt6);

        JButton jb1=new JButton("返回");
        jb1.setBounds(40,380,80,30);
        add(jb1);

        JButton jb2=new JButton("确认修改");
        jb2.setBounds(130,380,120,30);
        add(jb2);

        setVisible(true);

        StuDAO dao = new StuDAO();
        ArrayList<String> list = dao.FindSwing(id);

        //将查询到的数据集合填写进页面
        jt1.setText(list.get(0));
        jt2.setText(list.get(1));
        jt3.setText(list.get(2));
        jt4.setText(list.get(3));
        jt5.setText(list.get(4));
        jt6.setText(list.get(5));

        //设置返回键的操作
        jb1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                MenuSwing return1 =new MenuSwing();
                return1.setBounds((ScreenUtils.getScreenWidth()-850)/2,
                        (ScreenUtils.getScreenHeight()-450)/2,850,500);
                return1.setResizable(true);
                return1.setTitle("管理系统");
                setVisible(false);

            }
        });

        //设置确认修改键的操作
        jb2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //获取用户输入的数据
                /*注意：
                假如输入数据，
                性别不是“男”or“女”
                政治面貌不是“团员”or“党员”or“群众”
                班级不是数据库中depart表中已经存在的
                将会发生信息输入格式错误提示
                */
                String name = jt2.getText().trim();
                String sex = jt3.getText().trim();
                String country = jt4.getText().trim();
                String zzFace = jt5.getText().trim();
                String classs = jt6.getText().trim();
                StuDAO dao1 = new StuDAO();
                dao1.DMLUpdate(id,name,sex,country,zzFace,classs);
                //修改成功后自动返回菜单
                MenuSwing return2 =new MenuSwing();
                return2.setBounds((ScreenUtils.getScreenWidth()-850)/2,
                        (ScreenUtils.getScreenHeight()-450)/2,850,500);
                return2.setResizable(true);
                return2.setTitle("管理系统");
                setVisible(false);
            }
        });

    }
}
