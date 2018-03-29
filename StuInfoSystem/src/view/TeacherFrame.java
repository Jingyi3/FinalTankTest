 package view;

 import course.CourseManager;
 import student.*;

 import javax.swing.*;
 import java.awt.event.ActionEvent;
 import java.awt.event.ActionListener;

 class ManagerFrame extends JFrame implements ActionListener {// ����Ա����
     JPanel p1 = new JPanel();
     JPanel p2 = new JPanel();
     JPanel p3 = new JPanel();

     JButton btns = new JButton("ѧ����Ϣ����");
     JButton btnc = new JButton("�γ���Ϣ����");
     JButton btnClose = new JButton("�˳�����ϵͳ");
     //JLabel l = new JLabel("����Ա");

     ManagerFrame() {// ���췽��
         super("ѧ��ѡ��ϵͳ");
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
         if (e.getActionCommand() == "ѧ����Ϣ����")
             new StudentManager().display();
         if (e.getActionCommand() == "�γ���Ϣ����")
             new CourseManager("�γ���Ϣ����").display();
         if (e.getActionCommand() == "�˳�����ϵͳ") {
             System.exit(0);
         }
     }
 }

