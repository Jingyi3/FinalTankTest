package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 * JComboBox/Jlist/JScrollPane组件
 */

import java.awt.*;
import javax.swing.*;
public class Demo8_8 extends JFrame{

    JPanel jp1,jp2;
    JLabel jl1,jl2;
    JComboBox jcb1;
    JList l1;
    JScrollPane jsp1;

    public static void main(String [] args) {

        Demo8_8 win = new Demo8_8();
    }

    public Demo8_8 ()  {
        jp1 = new JPanel();
        jp2 = new JPanel();

        jl1 = new JLabel("您的籍贯是");
        jl2 = new JLabel("您喜欢去旅游的地方是");

        String[] jg = {"北京","上海","天津","火星"};
        jcb1 = new JComboBox(jg);
        //jcb2 = new JComboBox(jg);

        String [] dd = {"九寨沟","天池","故宫","东方明珠","天津","火星"};
        l1 = new JList(dd);
        //设置你希望显示多少个选项
        l1.setVisibleRowCount(2);
        jsp1 = new JScrollPane(l1);




        //设置布局
        this.setLayout(new GridLayout(3,1));

        //添加组件
        this.add(jp1);
        this.add(jp2);

        jp1.add(jl1);
        jp1.add(jcb1);

        jp2.add(jl2);
        jp2.add(jsp1);

        //设置
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(300,400);




    }
}
