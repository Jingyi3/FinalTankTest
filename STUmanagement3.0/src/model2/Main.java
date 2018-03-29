package model2;

import com.oracle.tools.packager.Log;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ZJYYY on 2018/3/6.
 */
public class Main extends JFrame implements ActionListener {
    //定义一些控件
    JPanel jPanle1, jPanle2;
    JLabel jLabel1,jLabel2;
    JButton jButton1,jButton2,jButton3,jButton4;
    JTable jTable;
    JScrollPane jScrollPane;
    JTextField jTextField;

    StuModel stuModel = new StuModel();

    public static void main(String [] args) {
        Main m = new Main();
    }

    public Main() {


        jPanle1 = new JPanel();
        //jTextField = new JTextField(10);
        jButton1 = new JButton("学生表管理");
        jButton1.setActionCommand("stu");
        jButton1.addActionListener(this);
        jButton2 =new JButton("课程表管理");
        jButton1.setActionCommand("course");
        jButton2.addActionListener(this);
        jButton3 = new JButton("选课表管理");
        jButton3.addActionListener(this);
        jButton1.setActionCommand("xuanke");


        //jLabel1 = new JLabel("请输入名字");
        //把各个部分加入到JPanel1
        //jPanle1.add(jLabel1);
        //jPanle1.add(jTextField);
        jPanle1.add(jButton1);
        jPanle1.add(jButton2);
        jPanle1.add(jButton3);



        //jPanle2 = new JPanel();
        //jPanle2 = new JPanel();

        //把各个按钮加入到JPanel2中
//        jPanle2.add(jButton2);
//        jPanle2.add(jButton3);
//        jPanle2.add(jButton4);

//        //创建一个数据模型对象
//        stuModel = new StuModel();
//        String paras[] = {"1"};
//        stuModel.queryStu("select * from stu where 1=?",paras);
//
//        //初始化JTable
//        jTable = new JTable(stuModel);
//
//        //初始化JScrollPane
//        jScrollPane = new JScrollPane(jTable);

        //把jsp放入到JFrame里面去

        this.add(jPanle1);



        this.setSize(400,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("stu")) {
            StuMange stu = new StuMange();

        } else if (e.getActionCommand().equals("course")) {

        } else if (e.getActionCommand().equals("xuanke")) {

        } else{
            Log.debug("啥都没点到");
        }

    }
}
