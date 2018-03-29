package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 *
 * 复选框和单选框案例
 */

import java.awt.*;
import javax.swing.*;

public class Demo8_7 extends JFrame {

    JPanel jp1,jp2,jp3;
    JButton jb1,jb2;
    JCheckBox jcb1,jcb2,jcb3;
    JRadioButton jrb1,jrb2;
    JLabel jl1,jl2;
    ButtonGroup bg;


    public static void main(String [] args){

        Demo8_7 win = new Demo8_7();

    }

    public Demo8_7 () {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jb1 = new JButton("注册用户");
        jb2 = new JButton("取消祖册");

        jl1 = new JLabel("你最喜欢的运动");
        jl2 = new JLabel("你的性别");

        jcb1 = new JCheckBox("篮球");
        jcb2 = new JCheckBox("足球");
        jcb3 = new JCheckBox("网球");

        jrb1 = new JRadioButton("男");
        jrb2 = new JRadioButton("女");

        //一定要把单选框组件放入到一个ButtonGroup中
        bg = new ButtonGroup();
        bg.add(jrb1);
        bg.add(jrb2);

        //设置布局管理
        this.setLayout(new GridLayout(3,1));

        //加入各个组件
        jp1.add(jl1);
        jp1.add(jcb1);
        jp1.add(jcb2);
        jp1.add(jcb3);

        jp2.add(jl2);
        jp2.add(jrb1);
        jp2.add(jrb2);

        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        //设置布局
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,200);
        this.setLocation(200,200);
        this.setTitle("健身房会员注册");









    }
}
