package Gui.LoginSwing.LoginTry;

import Gui.LoginSwing.ScreenUtils;
import Gui.MenuSwing.MenuSwing;
import Gui.MenuSwing.MenuSwingStu;
import 各项功能.StuDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * 系统登录的验证码验证，并且进行账户密码与数据库内user表内容的比对验证，两者都成功都能进入菜单
 */
public class LoginTry extends JFrame {
    public  LoginTry(int[] choice, String UserName, String PassWord) {
        setSize(200, 200);

        setDefaultCloseOperation(EXIT_ON_CLOSE);//设置关闭按钮
        setResizable(true);//窗口可以移动
        setLocationRelativeTo(null);
        setLayout(null);//布局方式

        //输入验证码标题
        JLabel j0 = new JLabel("请输入验证码：");
        j0.setBounds(30, 20, 150, 30);
        add(j0);

        //组装文本框
        JTextField pLable = new JTextField(15);
        pLable.setBounds(30, 50, 100, 30);
        add(pLable);


        //生成大小写字母与数字混合的验证码
        Random rs = new Random();
        String code = "";//定义一个字符记录生成随机字符
        //定义一个for循环，循环n次生成n个验证码
        for (int i = 0; i < 5; i++) {
            //生成一个随机字符
            //生成0,1,2三位数字，用来选择生成的字符是大小写字母还是数字
            int type = rs.nextInt(3);
            switch (type) {
                case 0:
                    //大写字母 （A 65 ~ Z 65+25）
                    char ch = (char) (rs.nextInt(26) + 65);
                    code += ch;
                    break;
                case 1:
                    //小写字母 （a 97 ~ z 97+25）
                    char ch1 = (char) (rs.nextInt(26) + 97);
                    code += ch1;
                    break;
                case 2:
                    //数字字符
                    code += rs.nextInt(10);//0~9
                    break;
            }
        }

        //组装放置验证码框
        JLabel jl = new JLabel(code);
        jl.setBounds(140, 50, 50, 30);
        add(jl);

        //设置确认键
        JButton jb = new JButton("确认");
        jb.setBounds(100, 100, 60, 20);
        add(jb);

        setVisible(true);

        //作为登录成功验证器，任何账户登录成功则返回上一个类关闭登录窗口,默认返回失败，为false


        //验证输入验证码的正确性，正确的话进入验证账户密码功能
        String finalCode = code;
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (finalCode.equals(pLable.getText().trim())) {
                    //验证码与输入字符一致，验证成功
                    JOptionPane.showMessageDialog(null, "验证成功");
                    //调用查询数据库的用户，分设两个验证，验证登录不同性质的账号
                    if (choice[0] == 1) {//登录管理员账号
                        try {
                            boolean flag = new StuDAO().Login(UserName, PassWord);
                            if (flag) {
                                MenuSwing menu = new MenuSwing();
                                dispose();//关闭当前验证窗口
                              //  LoginFrame.close();
                                JOptionPane.showMessageDialog(null, "登录成功");
                                menu.setBounds((ScreenUtils.getScreenWidth() - 850) / 2,
                                        (ScreenUtils.getScreenHeight() - 450) / 2, 850, 500);
                                menu.setResizable(true);
                                menu.setTitle("管理系统");
                            } else {
                                JOptionPane.showMessageDialog(null, "您输入账户或密码有误", "登录失败",
                                        JOptionPane.WARNING_MESSAGE);
                                dispose();
                            }
                        } catch (Exception ex) {
                            //创建连接失败提示窗口
                            JOptionPane.showMessageDialog(null, "请稍后在试", "连接失败",
                                    JOptionPane.WARNING_MESSAGE);
                            throw new RuntimeException(ex);
                        }
                        setVisible(false);
                    }else if (choice[0] == 2){//登录学生账号
                        boolean flag = new StuDAO().LoginStu(UserName,PassWord);
                        if (flag) {
                            MenuSwingStu menuStu = new MenuSwingStu();
                            dispose();
                            JOptionPane.showMessageDialog(null, "登录成功");
                            menuStu.setBounds((ScreenUtils.getScreenWidth() - 450) / 2,
                                    (ScreenUtils.getScreenHeight() - 350) / 2, 400, 300);
                            menuStu.setResizable(true);
                            menuStu.setTitle("管理系统");
                        } else {
                            JOptionPane.showMessageDialog(null, "您输入账户或密码有误", "登录失败",
                                    JOptionPane.WARNING_MESSAGE);
                            dispose();
                        }
                    }
                } else {
                    //验证码错误。
                    dispose();
                    jl.setText(" ");
                    JOptionPane.showMessageDialog(null, "验证码有误");
                }

            }
        });

    }
}