package Gui.LoginSwing;

import javax.swing.*;
import java.awt.*;

/**
 * 页面背景设置
 */
public class BackGroundPanel extends JPanel {
    //声明图片
    private Image backIcon;
    public BackGroundPanel(Image backIcon){
        this.backIcon = backIcon;
    }

    @Override
    public void paintComponent(Graphics g){
        //绘制背景
        super.paintComponent(g);
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}
