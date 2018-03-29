package com.test1;

/**
 * Created by ZJYYY on 2018/1/6.
 * qq聊天界面
 *
 */
import oracle.jvm.hotspot.jfr.JFR;

import java.awt.*;
import javax.swing.*;
public class Demo8_10 extends JFrame {

    JPanel jp1,jp2;
    JTextArea jTextArea;
    JScrollPane jScrollPane;

    JComboBox jComboBox;
    JTextField jTextField;
    JButton jb1;

    public static void main(String [] args) {

        Demo8_10 win = new Demo8_10();
    }

    public Demo8_10() {
        //jp1 = new JPanel();
        jp2 = new JPanel();
        jTextArea =new JTextArea();
        String [] chater = {"bush","ladeng"};
        jComboBox = new JComboBox(chater);
        jTextField = new JTextField(10);
        jb1 = new JButton("发送");
        jScrollPane = new JScrollPane(jTextArea);

        //设置布局

        //添加组件
        //jp1.add(jTextArea);

        jp2.add(jComboBox);
        jp2.add(jTextField);
        jp2.add(jb1);



        //设置窗体属性
        //this.add(jp1);
        this.add(jScrollPane);
        this.add(jp2,BorderLayout.SOUTH);


        this.setIconImage(new ImageIcon("images/img33.gif").getImage());
        this.setTitle("WeChat");
        this.setSize(500,200);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}
