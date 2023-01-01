package main;

import Gui.LoginSwing.LoginFrame;
import Gui.LoginSwing.ScreenUtils;

import javax.swing.*;

/**
 * 主程序
 * 调用构建登录页面类LoginFrame
 */
public class Main {
    public static void main(String[] args) {
        LoginFrame lf = new LoginFrame();
        lf.setBounds((ScreenUtils.getScreenWidth()-450)/2,
                (ScreenUtils.getScreenHeight()-350)/2,400,300);
        lf.setResizable(false);
        lf.setTitle("学生信息管理系统");
        lf.setVisible(true);
        lf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
