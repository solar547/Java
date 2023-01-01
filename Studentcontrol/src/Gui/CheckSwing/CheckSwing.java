package Gui.CheckSwing;

import Gui.LoginSwing.ScreenUtils;
import Gui.MenuSwing.MenuSwing;
import 各项功能.StuDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * 查询功能
 * 查询页面以及查询内容显示
 */
public class CheckSwing extends JFrame {

        //根据从菜单的总表选择的行数的学号来寻找学生完整信息
        public CheckSwing(String id)  {
            setSize(300,650);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setResizable(true);
            setLocationRelativeTo(null);
            setLayout(null);

            //设置页面组件
            JLabel j0=new JLabel("学生信息");
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

            final JLabel jt2=new JLabel();
            jt2.setBounds(100,120,130,30);
            add(jt2);

            JLabel j3=new JLabel("性别：");
            j3.setBounds(30,170,50,30);
            add(j3);

            final JLabel jt3=new JLabel();
            jt3.setBounds(100,170,130,30);
            add(jt3);

            JLabel j4=new JLabel("籍贯：");
            j4.setBounds(30,220,50,30);
            add(j4);

            final JLabel jt4=new JLabel();
            jt4.setBounds(100,220,130,30);
            add(jt4);

            JLabel j5=new JLabel("政治面貌：");
            j5.setBounds(30,270,70,30);
            add(j5);
            final JLabel jt5=new JLabel();
            jt5.setBounds(100,270,130,30);
            add(jt5);

            JLabel j6=new JLabel("班级：");
            j6.setBounds(30,320,50,30);
            add(j6);
            final JLabel jt6=new JLabel();
            jt6.setBounds(100,320,130,30);
            add(jt6);

            JLabel j7=new JLabel("专业：");
            j7.setBounds(30,370,50,30);
            add(j7);
            final JLabel jt7=new JLabel();
            jt7.setBounds(100,370,130,30);
            add(jt7);

            JLabel j8=new JLabel("学院：");
            j8.setBounds(30,420,50,30);
            add(j8);
            final JLabel jt8=new JLabel();
            jt8.setBounds(100,420,130,30);
            add(jt8);

            JLabel j9=new JLabel("院长：");
            j9.setBounds(30,470,50,30);
            add(j9);
            final JLabel jt9=new JLabel();
            jt9.setBounds(100,470,130,30);
            add(jt9);

            //设置按钮
            JButton jb1=new JButton("确认");
            jb1.setBounds(50,520,80,30);
            add(jb1);

            JButton jb2=new JButton("返回");
            jb2.setBounds(150,520,80,30);
            add(jb2);

            //调用方法获取数据，并用集合的方式存储
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
            jt7.setText(list.get(6));
            jt8.setText(list.get(7));
            jt9.setText(list.get(8));

            //设置确认键和返回键的反馈
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

            jb2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent event){
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
