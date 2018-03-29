package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 */
import java.awt.*;
import javax.swing.*;

public class Demo8_6 extends JFrame {

    JPanel jp1,jp2,jp3;
    JLabel jl1,jl2;
    JButton jb1,jb2;
    JTextField jtf1;
    JPasswordField jpf1;

    public static void main(String [] args) {

        Demo8_6 win = new Demo8_6();
    }

    public Demo8_6() {

        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();

        jl1 = new JLabel("用户名");
        jl2 = new JLabel("密  码");

        jb1 = new JButton("登录");
        jb2 = new JButton("取消");

        jtf1 = new JTextField(10);
        //jtf2 = new JTextField();

        jpf1 = new JPasswordField(10);

        //设置布局管理器

        this.setLayout(new GridLayout(3,1));

        //加入各个组件
        jp1.add(jl1);
        jp1.add(jtf1);
        jp2.add(jl2);
        jp2.add(jpf1);
        jp3.add(jb1);
        jp3.add(jb2);

        //加入到JFranme
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);

        //设置布局

        this.setSize(300,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);



    }
}
