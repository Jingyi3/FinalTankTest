package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 * 多种布局管理器组合使用
 *
 */
import java.awt.*;
import javax.swing.*;
public class Demo8_5 extends JFrame {
    JPanel jp1,jp2;
    JButton jb1,jb2,jb3,jb4,jb5,jb6;

    public static void main(String [] args) {
        Demo8_5 win = new Demo8_5();

    }

    public Demo8_5() {
        //创建组件
        //JPanel默认是FlowLayout
        jp1 = new JPanel();
        jp2 = new JPanel();

        jb1 = new JButton("西瓜");
        jb2 = new JButton("苹果");
        jb3 = new JButton("荔枝");
        jb4 = new JButton("葡萄");
        jb5 = new JButton("橘子");
        jb6 = new JButton("草莓");

        //设置布局管理

        //添加组件
        //JPanel
        jp1.add(jb1);
        jp1.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);
        jp2.add(jb5);

        //把Panel加入JFrame

        this.add(jp1, BorderLayout.NORTH);
        this.add(jb6,BorderLayout.CENTER);
        this.add(jp2,BorderLayout.SOUTH);

        this.setTitle("水果之家");
        this.setSize(300,150);
        this.setLocation(200,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




    }
}
