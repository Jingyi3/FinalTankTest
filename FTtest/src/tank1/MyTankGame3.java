package tank1;

/**
 * Created by ZJYYY on 2018/1/12.
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
 *
 *  【功能9】*
 *  可以分关打
 *
 *  【功能10】*
 *  可以在玩游戏的时候栈暂停和继续
 *
 *  【功能11】*
 *  可以记录玩家的成绩
 *
 *  【功能12】*
 *  java如何操作声音文件
 *
 *
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyTankGame3 extends JFrame {

    MyPanel mp = null;
    public static void main (String [] args) {
        MyTankGame3 tank = new MyTankGame3();
    }

    public MyTankGame3(){
        mp = new MyPanel();
        //启动线程
        Thread t1= new Thread(mp);
        t1.start();

        this.add(mp);
        this.addKeyListener(mp);
        this.setSize(400,300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}


//定义一个MyPanel（我自己的面板适用于绘图和实现绘图的区域）
class MyPanel extends JPanel implements KeyListener, Runnable {

    //定义一个我的坦克
    Hero hero = null;


    //定义敌人个数
    int enSize = 8;

    //定义炸弹集合
    Vector<Bomb> bombs = new Vector<Bomb>();

    //定义敌人的坦克【组】
    Vector<EnemyTank> ets = new Vector<EnemyTank>();

    //定义三张图片
    //三张图片才能组成一颗炸弹
    Image img1 = null;
    Image img2 = null;
    Image img3 = null;


    //MyPanel的构造函数
    public MyPanel() {
        //我的坦克
        hero = new Hero(100, 100);


        //初始化敌人的坦克
        for (int i = 0; i < enSize; i++) {
            //创建敌人的坦克对象
            EnemyTank et = new EnemyTank((i + 1) * 50, 0);
            //加入到敌人坦克组里
            ets.add(et);
            //每次动态创建一个坦克的时候 应当把这个新的坦克加入到EnemyTank的向量中
            et.setEts(ets);

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

//
//        try {
//            img1 = ImageIO.read(new File("/bomb_1.gif"));
//            img2 = ImageIO.read(new File("/bomb_2.gif"));
//            img3 = ImageIO.read(new File("/bomb_3.gif"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

        //画出自己的坦克
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

        //画出炸弹
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

    //写一个专门判断子弹石头击中敌人坦克的函数
    public void hitTank(Shot s, Tank et) {
        //判断该敌人坦克的方向
        //因为每个方向的坦克坐标范围不同
        //创建一颗炸弹，放入Vector中


        switch (et.direct) {
            //如果敌人坦克此时的方向是上或者是下
            case 0:
            case 1:
                if (s.x < et.x + 20 && s.x > et.x && s.y > et.y && s.y < et.y + 30) {
                    //击中了
                    //子弹和敌人死亡
                    s.isAlive = false;
                    //et.isAlive = false;
                    this.hitHeroMultipleTimes(et);

                    //创建一颗炸弹，放入Vector中
                    Bomb b = new Bomb(et.x, et.y);
                    bombs.add(b);
                }
                break;

            case 2:
            case 3:
                if (s.x < et.x + 30 && s.x > et.x && s.y > et.y && s.y < et.y + 20) {
                    //击中了
                    s.isAlive = false;
                    //et.isAlive = false;
                    this.hitHeroMultipleTimes(et);
                    //创建一颗炸弹，放入Vector中
                    Bomb b = new Bomb(et.x, et.y);
                    bombs.add(b);
                }
                //老师没有写break！！
                break;
        }
    }

    //hero打两下才会死 主角光环！！
    public void hitHeroMultipleTimes(Tank et) {
        if (et != hero) {
            et.isAlive = false;
        } else {
            hero.shotCount++;
            if (hero.shotCount == 2) {
                et.isAlive = false;
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
    public void hitMe(){
        //取出每一个敌人的坦克
        for(int i = 0; i < this.ets.size(); i++) {
            //取出敌人的坦克
            EnemyTank et = ets.get(i);

            //再取出每一颗子弹
            for(int j = 0; j < et.ss.size(); j++) {
                //取出子弹
                Shot enemyShot = et.ss.get(j);

                //这颗子弹是否击中了我的坦克
                this.hitTank(enemyShot,hero);
            }
        }
    }


}

