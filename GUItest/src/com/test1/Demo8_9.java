package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 */

import java.awt.*;
import javax.swing.*;

public class Demo8_9 extends JFrame {

    JSplitPane jsp;
    JList jList;
    JLabel jll;


    public static void main(String [] args) {

        Demo8_9 win = new Demo8_9();
    }

    public Demo8_9() {
       String [] words = {"apple","banbana","cat","debug","eat"};
       jList = new JList(words);

       jll = new JLabel(new ImageIcon("images/img1.jpg"));
       //创建可拆分窗格
       jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jList,jll);

       //可以伸缩
        jsp.setOneTouchExpandable(true);

       //设置布局管理器

        //添加组件
        this.add(jsp);
        //设置大小
        this.setSize(400,300);
        this.setLocation(200,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
