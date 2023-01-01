package Gui.LoginSwing;

import Gui.LoginSwing.LoginTry.LoginTry;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 登录页面的gui
 */
public class LoginFrame extends JFrame {
    //初始化，设置登录页面显示失败弹窗
    public LoginFrame(){
        try {
            init();
        } catch (Exception e) {
            System.out.println("页面加载失败");
            throw new RuntimeException(e);
        }
    }

    //登录页面布局
    void init() throws Exception{
        //设置窗口的内容
        BackGroundPanel bgPanel = new BackGroundPanel(ImageIO.read(new File(ClassLoader.getSystemResource("images/v2-8299d5eae2d7c4ac66450baad1a1496a_r.jpg").toURI())));
        bgPanel.setBounds(0,0,400,250);
        //组装登录相关元素，使用盒式布局
        Box vBox = Box.createVerticalBox();

        //组装标题
        Box TBox = Box.createHorizontalBox();
        TBox.add(Box.createHorizontalStrut(-10));
        JLabel null1 = new JLabel("   ");
        JLabel Titile = new JLabel("学生信息管理系统");
        Titile.setFont(new Font("微软雅黑",Font.BOLD,18));
        Titile.setForeground(new Color(120,150,4));
        TBox.add(null1);
        TBox.add(Titile);

        //组装用户名
        Box uBox = Box.createHorizontalBox();
        JLabel uLabel = new JLabel("用户名:");
        JTextField uField = new JTextField(15);
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);

        //组装密码
        Box pBox = Box.createHorizontalBox();
        JLabel pLabel = new JLabel("  密 码:");

        JTextField pField = new JTextField(15);
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);

        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton loginBtn = new JButton("登录");
        JButton registerB = new JButton("退出");
        btnBox.add(registerB);
        btnBox.add(Box.createHorizontalStrut(50));
        btnBox.add(loginBtn);

        //组装单选按钮
        Box JRBox = Box.createHorizontalBox();
        ButtonGroup group = new ButtonGroup();
        JRadioButton user = new JRadioButton("管理员");
        JRadioButton Stuuser = new JRadioButton("学生");
        group.add(user);
        group.add(Stuuser);
        JRBox.add(user);
        JRBox.add(Box.createHorizontalStrut(20));
        JRBox.add(Stuuser);

        //将所有组件组装起来
        vBox.add(Box.createVerticalStrut(30));
        vBox.add(TBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(10));
        vBox.add(JRBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);


        bgPanel.add(vBox);
        add(bgPanel);
        pack();

        //定义变量来记录单选按钮的选择内容,当choice数组第一个数是1时表示选择了管理员账号，2表示选择登录学生账号
        final int[] choice = {0};
        //单选按钮的监听器：登录管理员账号或者是学生账号
        user.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 1;
            }
        });
        Stuuser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                choice[0] = 2;
            }
        });

        //验证码查验并验证密码
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (choice[0] == 0){//弹出当前单选按钮为选择登录的账户类型的提醒
                    JOptionPane.showMessageDialog(null, "请选择登录账户类型", "注意",
                            JOptionPane.WARNING_MESSAGE);
                }else {
                    //获取用户输入的数据
                    String UserName = uField.getText().trim();
                    String PassWord = pField.getText().trim();
                    //验证码验证
                    //flag为登录验证器，当登录成功时关闭登录页面，否则则不关闭
                    new LoginTry(choice, UserName, PassWord);
                }
            }
        });

        //退出系统
        registerB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"退出成功，欢迎下次登录");
                System.exit(0);
            }
        });

    }

}
