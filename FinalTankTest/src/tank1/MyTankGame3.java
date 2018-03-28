package tank1;

/**
 * Created by ZJYYY on 2018/1/12.
 * ------------------------------------------------
 *  还没有解决的问题：
 *  【bug】
 *  1. 点击两次 按钮 都会出现频闪问题
 *  2. 不会调用出GameOverPanel 不知道在哪里写一个循环判断hero死没有的判断
 *  或者在hero死了的时候怎么调用GOP
 *  3. 声音文件无法播放
 *
 *  还有想实现的功能
 *  1. 游戏开始界面 频闪结束后 应该直接开始游戏
 *  2. 实现关卡控制 以及 高级模式设置（用户自己选择难度和个项指标）
 *
 * ------------------------------------------------
 * 【功能0】
 * 实现小车的移动
 *
 * 【功能1】
 * 实现功能子弹可以连发
 * 最多连发五颗子弹
 *
 * 【功能2】
 * 击中敌人坦克的时候敌人的坦克消失
 * 消失的受伴随爆炸的效果
 *
 * 1.写一个专门判断是否击中敌人的坦克的函数--卸载Panel面板上
 * 2. 什么地方调用该函数
 *
 *  【功能3】
 * 击中敌人的时候出现爆炸的效果
 * 1. 先准备三张图片
 *
 *  【功能4】
 * 让敌人的坦克可以自由的上下左右移动
 *
 *  【功能5】
 * 控制我方坦克和敌人坦克 在规定的范围内移动
 *
 *  【功能6】
 * 当敌人的坦克击中我的t坦克时候
 * 我的坦克会爆炸
 *
 *  【功能7】*
 * 控制敌人的坦克互相不会重叠
 * 7.1 决定把 判断是否会碰撞的函数写到自己的EnemyTank类中
 *     因为这是一个坦克的能力
 *
 *  【功能8】*
 *  防止 敌人的坦克 重叠运动
 *  8.1做一个开始的Panel--是一个空的==主要用于提示
 *  8.2 闪烁效果
 *
 *  【功能9】*
 *  可以分关打
 *
 *  【功能10】*
 *  可以在玩游戏的时候暂停和继续
 *  10.1 当用户点击暂停的时候，把子弹和坦克的速度设置为零
 *  10.2 并让坦克的方向不要变化 否则坦克原地打转
 *
 *
 *  【功能11】*
 *  可以记录玩家的成绩
 *  11.1 使用文件流
 *  11.2（大型游戏）使用数据库 cs结构或者bs结构 保存在服务器中 单机版用文件保存
 *  11.3 单写一个记录类 来记录玩家
 *  11.4先完成保存 共击毁多少辆敌人坦克的功能
 *  11.5 存盘退出--记录敌人的坦克坐标，并可以恢复
 *
 *
 *  【功能12】*
 *  java如何操作声音文件
 *
 *
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyTankGame3 extends JFrame implements ActionListener {

    MyPanel mp = null;

    //定义一个开始面板
    MyStartPanel  myStartPanel = null;

    //定义一个游戏结束面板
    GameOverPanle gameOverPanle = null;

    //做出我的菜单
    JMenuBar jMenuBar = null;
    //开始游戏
    JMenu jMenu1 =null;
    JMenu jMenu2 =null;
    JMenu jMenu3 =null;
    JMenuItem jMenuItem1_1 = null;
    JMenuItem jMenuItem1_2 = null;
    JMenuItem jMenuItem1_3 = null;
    JMenuItem jMenuItem1_4 = null;
    JMenuItem jMenuItem2_1 = null;
    JMenuItem jMenuItem2_2 = null;
    JMenuItem jMenuItem3_1 = null;
    JMenuItem jMenuItem3_2 = null;
    //


    public static void main (String [] args) {
        MyTankGame3 tank = new MyTankGame3();

    }

    public MyTankGame3(){
        //创建菜单及菜单选项
        jMenuBar = new JMenuBar();
        jMenu1 = new JMenu("游戏(G)");
        //设置快捷方式
        jMenu1.setMnemonic('G');
        jMenuItem1_1 = new JMenuItem("开始新游戏(N)");
        jMenuItem1_1.setMnemonic('N');
        //对jMenuItem1响应!!
        jMenuItem1_1.addActionListener(this);
        jMenuItem1_1.setActionCommand("newgame");
        jMenuItem1_2 = new JMenuItem("退出游戏(E)");
        jMenuItem1_2.setMnemonic('E');
        jMenuItem1_2.addActionListener(this);
        jMenuItem1_2.setActionCommand("exit");
        jMenuItem1_3 = new JMenuItem("存盘退出(S)");
        jMenuItem1_3.setMnemonic('S');
        jMenuItem1_3.addActionListener(this);
        jMenuItem1_3.setActionCommand("saveExit");
        jMenuItem1_4 = new JMenuItem("继续上局游戏");
        jMenuItem1_4.addActionListener(this);
        jMenuItem1_4.setActionCommand("continue");

        jMenu2 = new JMenu("设置(I)");
        jMenu2.setMnemonic('I');
        jMenu3 = new JMenu("帮助(H)");
        jMenu3.setMnemonic('H');

        jMenu1.add(jMenuItem1_1);
        jMenu1.add(jMenuItem1_2);
        jMenu1.add(jMenuItem1_3);
        jMenu1.add(jMenuItem1_4);
        jMenuBar.add(jMenu1);
        jMenuBar.add(jMenu2);
        jMenuBar.add(jMenu3);
        //是JMenuBar 不是MenuBar
        this.setJMenuBar(jMenuBar);

        //创建开始面板
        myStartPanel = new MyStartPanel();
        this.add(myStartPanel);
        //启动线程
        Thread t2 = new Thread(myStartPanel);
        t2.start();

        this.setSize(600,500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void startGO(){
        //创建开始面板
        gameOverPanle = new GameOverPanle();
        this.remove(myStartPanel);
        this.remove(mp);
        this.add(gameOverPanle);
        //启动线程
        Thread t3 = new Thread(gameOverPanle);
        t3.start();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //【有一个大bug！！！！！！！！！！！！】连续点两次开始游戏 会频闪
        //对用户不同的点击做出不同的处理
        if(e.getActionCommand().equals("newgame")) {

            //创建真正战场的面板
            mp = new MyPanel("newGame");

            //启动线程
            Thread t1= new Thread(mp);
            t1.start();
            //先删除旧的面板==开始面板
            this.remove(myStartPanel);

            this.add(mp);
            //注册监听
            this.addKeyListener(mp);
            //显示 刷新JFrame
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        }

        //用户退出系统
        if(e.getActionCommand().equals("exit")){
            //保存击毁敌人数量
            Recorder.keepRecord();
            System.exit(0);
        }

        //用户点击存盘退出--坦克坐标的保存与恢复
        if(e.getActionCommand().equals("saveExit")) {
            //保存面板上坦克的数量 和 敌人的坐标 和总共击毁的敌人数量
            Recorder rc = new Recorder();
            rc.setAliveEnemys(mp.ets);//ets在MyPanel中
            rc.keepRecAndEnenmy();

            System.exit(0);
        }

        //用户选择继续上一局游戏
        if(e.getActionCommand().equals("continue")){
            //创建真正战场的面板
            mp = new MyPanel("continue");

            //启动线程
            Thread t1= new Thread(mp);
            t1.start();
            //先删除旧的面板==开始面板
            this.remove(myStartPanel);

            this.add(mp);
            //注册监听
            this.addKeyListener(mp);
            //显示 刷新JFrame
            this.setVisible(true);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}


//这个面板用于提示第几关
class MyStartPanel extends JPanel implements Runnable{
    //开关
    int times = 0;

    public void paint(Graphics g) {
        super.paint(g);
        //画出游戏面板

        g.fillRect(0,0,400,300);


        if(times % 2 == 0) {
            g.setColor(Color.CYAN);
            //提示信息
            Font myFont = new Font("Marker Felt", Font.BOLD, 30);
            g.setFont(myFont);
            g.drawString("Stage : 1", 150, 150);
        }
    }
    @Override
    public void run() {
        while (true) {
            //休眠一秒
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            times++;
            //重画
            this.repaint();

            //[自己写的]如果闪了10s就停下来
            if(times == 50) {
                break;
            }
        }
    }
}

//游戏结束面板
class GameOverPanle extends JPanel implements Runnable{

    //开关
    int times = 0;
    Boolean gameOn = true;

    public void paint(Graphics g) {
        super.paint(g);
        //画出游戏面板
        g.fillRect(0,0,400,300);

        if(times % 2 == 0) {
            g.setColor(Color.CYAN);
            //提示信息
            Font myFont = new Font("Marker Felt", Font.BOLD, 30);
            g.setFont(myFont);
            g.drawString("GAME OVER", 150, 150);
        }

        if(gameOn == false) {
            g.fillRect(0,0,400,300);
        }


    }

    @Override
    public void run() {
        while (true) {
            //休眠一秒
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            times++;
            //重画
            this.repaint();

            //[自己写的]如果闪了10s就停下来
            if(times == 25) {
                gameOn = false;
                break;
            }
        }
    }
}

//定义一个MyPanel（我自己的面板适用于绘图和实现绘图的区域）
class MyPanel extends JPanel implements KeyListener, Runnable {

    //定义一个我的坦克
    Hero hero = null;
    //判断是继续上一句 还是 新游戏
    //String flag = "newGame";
    //定义敌人个数
    int enSize = 3;

    //定义炸弹集合
    Vector<Bomb> bombs = new Vector<Bomb>();

    //定义敌人的坦克【组】
    Vector<EnemyTank> ets = new Vector<EnemyTank>();
    Vector<Node> nodes = new Vector<Node>();

    //定义三张图片
    //三张图片才能组成一颗炸弹
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;


    //MyPan
    // el的构造函数
    public MyPanel(String flag) {
        //恢复记录====写在最前面
        Recorder.getRecord();

        //我的坦克
        hero = new Hero(100, 100);

        if(flag.equals("newGame")) {

            //初始化敌人的坦克
            for (int i = 0; i < enSize; i++) {
                //创建敌人的坦克对象
                EnemyTank et = new EnemyTank((i + 1) * 50, 0);
                //每次动态创建一个坦克的时候 应当把这个新的坦克加入到EnemyTank的向量中
                et.setEts(ets);

                //加入到敌人坦克组里
                ets.add(et);

                //初始化敌人坦克
                et.setColor(1);
                et.setDirect(1);
                et.setSpeed(3);

                //启动敌人的坦克
                Thread t = new Thread(et);
                t.start();

                //给敌人的坦克加入一颗子弹
                Shot s = new Shot(et.x + 10, et.y + 30, et.direct);
                //加入敌人
                et.ss.add(s);
                //启动线程
                Thread t2 = new Thread(s);
                t2.start();
                //此时 敌人的坦克已经再发子弹了，但是你没有画，所以无法显示出仍和子弹，所以现在应该去paint画出敌人的子弹
            }
        } else {
            nodes = new Recorder().getNodesAndAllEnemy();
            //初始化敌人的坦克
            for (int i = 0; i < nodes.size(); i++) {

                Node node =nodes.get(i);
                //创建敌人的坦克对象
                EnemyTank et = new EnemyTank(node.x, node.y);
                //每次动态创建一个坦克的时候 应当把这个新的坦克加入到EnemyTank的向量中
                et.setEts(ets);

                //加入到敌人坦克组里
                ets.add(et);

                //初始化敌人坦克
                et.setColor(1);
                et.setDirect(node.direct);
                et.setSpeed(3);

                //启动敌人的坦克
                Thread t = new Thread(et);
                t.start();

                //给敌人的坦克加入一颗子弹
                Shot s = new Shot(et.x + 10, et.y + 30, et.direct);
                //加入敌人
                et.ss.add(s);
                //启动线程
                Thread t2 = new Thread(s);
                t2.start();
                //此时 敌人的坦克已经再发子弹了，但是你没有画，所以无法显示出仍和子弹，所以现在应该去paint画出敌人的子弹
            }
        }


//        //老师教的从第一张图开始爆炸的效果  但是没用！！
//        try {
//            img1 = ImageIO.read(new File("/bomb_1.gif"));
//            img2 = ImageIO.read(new File("/bomb_2.gif"));
//            img3 = ImageIO.read(new File("/bomb_3.gif"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //播放声音
        AePlayerWave apw = new AePlayerWave("/Users/ZJYYY/Desktop/cs-resource/javaimages/111.wav");
        apw.start();
        //初始化三张图片
        img1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.gif"));
        img2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.gif"));
        img3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.gif"));
    }


    //重写JPanel中的paint函数
    @Override
    public void paint(Graphics g) {
        //继承父类的构造函数
        super.paint(g);

        //将爆炸图片放入panel中
        g.drawImage(img1, 100, 100, 30, 30, this);
        g.drawImage(img2, 100, 100, 30, 30, this);
        g.drawImage(img3, 100, 100, 30, 30, this);

        //画出游戏面板
        g.fillRect(0, 0, 400, 300);

        //画出提示信息
        this.showInfo(g);

        //画出自己的坦克
        this.drawMytank(g);

        //画出敌人的坦克
        this.drawEnemyTank(g);

        //画出炸弹
        this.drawBoom(g);

    }


    //画出提示信息
    public void showInfo(Graphics g) {
        //画出提示信息坦克（该坦克不参与战斗）
        //画出敌人的坦克 type=1
        this.drawTank(90,330,g,0,1);
        g.setColor(Color.black);
        g.drawString(Recorder.getEnemyNum()+"",120,350);//+"可以将数字转成string

        //画出我的坦克
        this.drawTank(210,330,g,0,0);
        g.setColor(Color.black);
        g.drawString(Recorder.getMyLife()+"",240,350);

        //画出玩家的总成绩
        this.drawTank(430,60,g,0,1);
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemy()+"",470,80);


        g.setColor(Color.black);
        Font f = new Font("宋体",Font.BOLD,20);
        g.setFont(f);
        g.drawString("您的总成绩",420,30);

    }

    //画出自己的坦克
    public void drawMytank (Graphics g) {
        if (hero.isAlive) {
            this.drawTank(hero.getX(), hero.getY(), g, this.hero.getDirect(), 0);
        }

        //画出我的坦克的子弹从ss中取出每一刻子弹并绘制
        for (int i = 0; i < hero.ss.size(); i++) {

            Shot myShot = hero.ss.get(i);

            //画出一个子弹
            if (myShot != null && myShot.isAlive == true) {
                g.draw3DRect(myShot.x, myShot.y, 1, 1, false);
            }

            //如果我的子弹超出边界他就死亡了====不能写i
            if (myShot.isAlive == false) {
                //从向量ss中删除掉该子弹
                hero.ss.remove(myShot);
            }
        }

    }

    //画出敌人的坦克
    public void drawEnemyTank(Graphics g) {
        //画出敌人的坦克
        for (int i = 0; i < ets.size(); i++) {
            EnemyTank et = ets.get(i);
            et.setSpeed(2);

            if (et.isAlive ) {
                this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 1);

                for (int j = 0; j < et.ss.size(); j++) {
                    //取出一个子弹
                    Shot enemyShot = et.ss.get(j);

                    //画出一个子弹
                    if (enemyShot.isAlive) {
                        g.draw3DRect(enemyShot.x, enemyShot.y, 1, 1, false);
                    } else {
                        //如果子弹超出边界 他就死亡了 然后应该有新的子弹添加进来
                        et.ss.remove(enemyShot);
                    }
                }

            }
        }

    }

    //画出炸弹
    public void drawBoom(Graphics g) {

        for (int i = 0; i < bombs.size(); i++) {
            //取出一颗炸弹
            Bomb b = bombs.get(i);
            //
            if (b.life > 6) {
                g.drawImage(img1, b.x, b.y, 30, 30, this);

            } else if (b.life > 3) {
                g.drawImage(img2, b.x, b.y, 30, 30, this);

            } else if (b.life > 0) {
                g.drawImage(img3, b.x, b.y, 30, 30, this);

            }
            //每画过一次，让生命值减少
            b.lifeDown();
            //如果炸弹生命值==0 我们认为可以从向量中bombs移除炸弹
            if (b.life == 0) {
                bombs.remove(b);
            }

        }

    }


    //画出坦克的函数
    public void drawTank(int x, int y,Graphics g, int direct, int type) {
        //判断是什么类型的坦克
        //0--我的坦克
        //1--敌人的坦克
        switch(type) {
            case 0:
                g.setColor(Color.pink);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        //判断移动方向，上下左右
        //0--
        //1--
        //2--
        //3--
        switch (direct) {
            //向上
            case 0:

                //1.画出左面的矩形
                g.fill3DRect(x,y,5,30,false);
                //2. 画出右面的矩形
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,20,false);
                g.fillOval(x+5,y+10,10,10);
                g.drawLine(x+10,y+15,x+10,y);
                break;

            //向下
            case 1:
                g.fill3DRect(x,y,5,30,false);
                g.fill3DRect(x+15,y,5,30,false);
                g.fill3DRect(x+5,y+5,10,20,false);
                g.fillOval(x+5,y+10,10,10);
                g.drawLine(x+10,y+15,x+10,y+30);
                break;


            //向左
            case 2:
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x,y+15,30,5,false);
                g.fill3DRect(x+5,y+5,20,10,false);
                g.fillOval(x+10,y+5,10,10);
                g.drawLine(x+15,y+10,x,y+10);
                break;


            //向右
            case 3:
                g.fill3DRect(x,y,30,5,false);
                g.fill3DRect(x,y+15,30,5,false);
                g.fill3DRect(x+5,y+5,20,10,false);
                g.fillOval(x+10,y+5,10,10);
                g.drawLine(x+15,y+10,x+30,y+10);
                break;

        }
    }

    //键盘控制
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if(e.getKeyCode() == KeyEvent.VK_S) {
            this.hero.setDirect(1);
            this.hero.moveDown();

        } else if(e.getKeyCode() == KeyEvent.VK_W) {
            this.hero.setDirect(0);
            this.hero.moveUp();

        } else if(e.getKeyCode() == KeyEvent.VK_A) {
            this.hero.setDirect(2);
            this.hero.moveLeft();

        } else if(e.getKeyCode() == KeyEvent.VK_D) {
            this.hero.setDirect(3);
            this.hero.moveRight();

        }


        //单独拿出来保证可以【一边跑一边发】子弹
        if(e.getKeyCode() == KeyEvent.VK_J) {
            //按下J键 发射子弹 且【最多发五颗子弹】
            if(this.hero.ss.size() < 5) {
                this.hero.shotEnemy();
            }
        }
        //！！关键！！
        //调用repaint函数来重新绘制界面
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    //用线程实现子弹的发射
    //重写Runnable接口的run函数
    @Override
    public void run() {
        //每隔100ms 重画
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //我的坦克是否击中了别人的坦克
            this.hitEnemyTank();

            //判断敌人的子弹是否击中我的坦克
            this.hitMe();

            this.repaint();
        }
    }

    //判断我的子弹是否击中敌人的坦克
    public void hitEnemyTank() {
        //判断子弹是否击中敌人的坦克
        //两层循环
        for(int i = 0; i < hero.ss.size(); i++) {
            //取出子弹
            Shot myShot = hero.ss.get(i);
            if(myShot.isAlive) {

                for (int j = 0; j < ets.size(); j++) {
                    //取出一个敌人坦克
                    EnemyTank et = ets.get(j);

                    if( et.isAlive) {
                        hitTank(myShot,et);
                    }
                }
            }
        }
    }
    //判断敌人的子弹是否击中我的坦克
    public void  hitMe(){
        //取出每一个敌人的坦克
        for(int i = 0; i < this.ets.size(); i++) {
            //取出敌人的坦克
            EnemyTank et = ets.get(i);

            //再取出每一颗子弹
            for(int j = 0; j < et.ss.size(); j++) {
                //取出子弹
                Shot enemyShot = et.ss.get(j);

                //判断我的坦克是否活着--》避免反复爆炸
                if(hero.isAlive) {
                    //这颗子弹是否击中了我的坦克
                    this.hitTank(enemyShot, hero);
                }
            }
        }
    }

    //写一个专门判断子弹是否击中坦克的函数
    public void hitTank(Shot s, Tank tank) {
        //判断该敌人坦克的方向
        //因为每个方向的坦克坐标范围不同
        //创建一颗炸弹，放入Vector中
        switch (tank.direct) {
            //如果敌人坦克此时的方向是上或者是下
            case 0:
            case 1:
                if (s.x < tank.x + 20 && s.x > tank.x && s.y > tank.y && s.y < tank.y + 30) {
                    //击中了
                    //子弹和敌人死亡
                    s.isAlive = false;
                    //et.isAlive = false;
                    this.hitHeroMultipleTimes(tank);
                    //创建一颗炸弹，放入Vector中
                    Bomb b = new Bomb(tank.x, tank.y);
                    bombs.add(b);
                }
                break;

            case 2:
            case 3:
                if (s.x < tank.x + 30 && s.x > tank.x && s.y > tank.y && s.y < tank.y + 20) {
                    //击中了
                    s.isAlive = false;
                    //et.isAlive = false;
                    this.hitHeroMultipleTimes(tank);
                    //创建一颗炸弹，放入Vector中
                    Bomb b = new Bomb(tank.x, tank.y);
                    bombs.add(b);
                }
                break;
        }
    }

    //hero打两下才会死 主角光环！！
    public void hitHeroMultipleTimes(Tank tank) {
        if (tank != hero) {
            tank.isAlive = false;
            Recorder.reduceEnNum();
            Recorder.addEnNumRec();

        } else {
            hero.shotCount++;
            Recorder.reduceMyLife();
            if (Recorder.getMyLife() == 0) {
                tank.isAlive = false;

            }
        }
    }
}

