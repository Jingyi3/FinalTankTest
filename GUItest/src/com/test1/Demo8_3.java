package com.test1;

import javax.swing.*;

/**
 * Created by ZJYYY on 2018/1/6.
 * 功能：FlowLayout 流式布局管理器
 *
 */

import java.awt.*;
import javax.swing.*;

public class Demo8_3 extends JFrame {

    JButton jb1, jb2,jb3,jb4, jb5, jb6;

    public static void main(String [] args) {

        Demo8_3 b = new Demo8_3();

    }

    public Demo8_3() {
        //创建组件
        jb1 = new JButton("关羽");
        jb2 = new JButton("张飞");
        jb3 = new JButton("赵云");
        jb4 = new JButton("马超");
        jb5 = new JButton("黄忠");
        jb6 = new JButton("魏延");

        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        this.add(jb5);
        this.add(jb6);



        //设置布局管理器
        //this.setLayout(new FlowLayout(FlowLayout.TRAILING));
        //流式布局默认居中对齐


        //设置窗体属性
        this.setTitle("流式布局演示");
        this.setSize(400,200);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //显示窗体
        this.setVisible(true);





    }
}
