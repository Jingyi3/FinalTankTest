package com.test1;

/**
 * Created by ZJYYY on 2018/1/4.
 *
 * 功能：gui界面开发演示
 */
//引入包
import java.awt.*;
import javax.swing.*;

public class Demo8_1 extends JFrame {
    
    JButton jb = null;

    public static void main (String [] args) {
        Demo8_1 d1 = new Demo8_1();

    }

    //构造函数
    public Demo8_1 () {
        //JFrame是一个顶层容器类（可以添加其他的swing组件类）
        //JFrame jf = new JFrame();
        jb = new JButton("I am a button!");
        //给窗体设置标题
        this.setTitle("hello");

        //按照像素设置大小
        this.setSize(200,200);
        //添加jbutton 最贱
        this.add(jb);

        this.setLocation(100,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
