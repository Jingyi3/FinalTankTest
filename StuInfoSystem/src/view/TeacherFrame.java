 package view;

 import course.CourseManager;
 import student.*;

 import javax.swing.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

 class ManagerFrame extends JFrame implements ActionListener {// 管理员界面
     JPanel p1 = new JPanel();
     JPanel p2 = new JPanel();
     JPanel p3 = new JPanel();

     JButton btns = new JButton("学生信息管理");
     JButton btnc = new JButton("课程信息管理");
     JButton btnClose = new JButton("退出管理系统");
     //JLabel l = new JLabel("管理员");

     ManagerFrame() {// 构造方法
         super("学生选课系统");
         setSize(350, 200);

         //p1.add(l);
         p1.add(btns);
         p2.add(btnc);
         p3.add(btnClose);
         add("North", p1);
         add("Center", p2);
         add("South", p3);
         btns.addActionListener(this);
         btnc.addActionListener(this);
         /*btnsc.addActionListener(this);
         btng.addActionListener(this);
         btnu.addActionListener(this);*/
         btnClose.addActionListener(this);
         this.setResizable(false);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         show();
     }

     public void actionPerformed(ActionEvent e) {
         if (e.getActionCommand() == "学生信息管理")
             new StudentManager().display();
         if (e.getActionCommand() == "课程信息管理")
             new CourseManager("课程信息管理").display();
         if (e.getActionCommand() == "退出管理系统") {
             System.exit(0);
         }
     }
 }

