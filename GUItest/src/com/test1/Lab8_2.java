package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 * 边界布局 BorderLayout演示
 * 1. 继承JFrame
 * 2. 定义你需要的组件
 * 3. 创建组件（在构造函数中）
 * 4. 添加组件
 * 5. 窗体设置
 */

import java.awt.*;
import javax.swing.*;

public class Lab8_2 extends JFrame {

    //定义组件
    JButton jb1, jb2, jb3, jb4,jb5;


    public static void main (String [] args){

        Lab8_2 a = new Lab8_2();

    }

    public Lab8_2(){
        jb1 = new JButton("中部");
        jb2 = new JButton("北部");
        jb3 = new JButton("东部");
        jb4 = new JButton("南部");
        jb5 = new JButton("西部");

        //添加各个组件
        this.add(jb1, BorderLayout.CENTER);
        this.add(jb2, BorderLayout.NORTH);
        this.add(jb3, BorderLayout.EAST);
        this.add(jb4, BorderLayout.SOUTH);
        this.add(jb5, BorderLayout.WEST);
        //设置窗体属性
        this.setTitle("边界布局演示");
        this.setSize(300,200);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //显示窗体
        this.setVisible(true);

    }

}
