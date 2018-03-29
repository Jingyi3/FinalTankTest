package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 *
 * 功能：网格布局GridLayout
 */
import java.awt.*;
import javax.swing.*;

public class Demo8_4 extends JFrame {

    int size = 9;
    JButton jbs[] = new JButton[size];


    public  static void main(String [] args) {

        Demo8_4 c = new Demo8_4();

    }

    public Demo8_4() {
        //创建组件
        for(int i=0; i < size; i++) {
            jbs[i] = new JButton(String.valueOf(i+1));
        }

        //设置网格布局管理器
        //设置行数和列数
        this.setLayout(new GridLayout(3,3,10,10));

        //添加组件
        for(int i=0; i < size; i++) {
            this.add(jbs[i]);
        }

        //设置窗体属性
        this.setTitle("网格布局案例");
        this.setSize(300,200);
        this.setLocation(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //显示窗体
        this.setVisible(true);
    }

}
