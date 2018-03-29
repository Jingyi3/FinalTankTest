package tank1;

/**
 * Created by ZJYYY on 2018/1/7.
 */

import java.util.Vector;

//炸弹类
//因为炸弹在原地爆炸--不会造成坐标的改变 所以不使用线程
class Bomb {
    //定义炸弹的坐标
    int x;
    int y;
    //炸弹的生命
    int life = 9;
    boolean isAlive = true;

    //初始化
    public Bomb(int x,int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void lifeDown () {
        if(life > 0) {
            life--;
        } else {
            isAlive = false;
        }
    }
}


//子弹类
//坐标变化 线程
class Shot implements Runnable{
    int x;
    int y;
    int direct;
    int speed = 2;
    //子弹是否还活着
    boolean isAlive = true;


    public Shot(int x, int y,int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    @Override
    public void run() {
        while(true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch(direct) {
                case 0://shang
                    y = y-speed;
                    break;
                case 1://xia
                    y = y+speed;
                    break;
                case 2://zuo
                    x = x-speed;
                    break;
                case 3://you
                    x = x+speed;
                    break;
            }


            //子弹何时死亡？？
            //System.out.println("坐标哦是"+x+"，"+y);

            //判断子弹是否碰到边缘
            if(x < 0 || x > 400 || y < 0 || y > 300) {
                this.isAlive = false;
                break;
            }

        }
    }
}

//坦克类
//需要线程安全使用Vector
class Tank {
    //坦克的横坐标
    int x = 0;
    int y = 0;
    //坦克方向0up 1 down 2ringht 3 left
    int direct = 0;
    //设置坦克的速度
    int speed = 5;
    boolean isAlive = true;



    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    //颜色
    int color;


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public Tank (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDirect() {
        return direct;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}


//我的坦克
class Hero extends Tank {

    //子弹
    Shot s = null;
    int shotCount = 0;

    //为了连发 使用集合
    //Vector是线程安全的
    Vector<Shot> ss = new Vector<Shot>();


    public Hero (int x, int y) {
        super(x,y);
    }

    //开火
    public void shotEnemy() {


        switch(this.direct) {
            case 0:
                //创建一颗子弹
                s = new Shot(x+10,y,0);
                //把子弹加入到向量ss中
                ss.add(s);
                break;
            case 1:
                s = new Shot(x+10,y+30,1);
                ss.add(s);
                break;
            case 2:
                s = new Shot(x,y+10,2);
                ss.add(s);
                break;
            case 3:
                s = new Shot(x+30,y+10,3);
                ss.add(s);
                break;
        }

        //启动子弹线程
        Thread t = new Thread(s);
        t.start();


    }

    //坦克移动

    //up
    public void moveUp() {
        y = y - speed;
    }
    //down
    public void moveDown() {
        y = y + speed;
    }
    //left
    public void moveLeft() {
        x = x - speed;
    }
    //right
    public void moveRight() {
        x = x + speed;
    }
}

//敌人的坦克
class EnemyTank extends Tank implements Runnable {

    int times = 0;
    Shot s = null;

    //定义一个向量--可以访问到MyPanel上所有敌人坦克
    Vector<EnemyTank> ets = new Vector<EnemyTank>();

    //为了连发 使用集合
    //Vector是线程安全的 定义一个向量 可以存放敌人的子弹
    Vector<Shot> ss = new Vector<Shot>();


    //敌人添加子弹应该在刚刚创建坦克的时候和敌人的坦克子弹死亡之后
    public EnemyTank(int x, int y) {
        super(x,y);
    }


    //得到MyPanel的敌人坦克向量
    public void setEts(Vector<EnemyTank> vv) {
        this.ets = vv;
    }

    //判断是否碰到了别的敌人的坦克
    public boolean isTouchOtherEnemy() {
        boolean b =  false;

        switch (this.direct) {

            case 0://坦克在向上移动
                //System.out.println("我是第"+this+"坦克"+"x="+this.x+"  y="+this.y);


                //取出所有的敌人坦克
                for(int i = 0; i < ets.size(); i++) {
                //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //！！我没有想到的点： 不能自己和自己比较！！
                    if(et != this) {
                        //如果敌人的方向 是向下或者向上
                        if (et.direct == 0 || et.direct == 1) {

                            //this坦克 方向向上 保证他的上面两个点 不会进入别的坦克中
                            //以下两种情况this进入了别的坦克

                            if(this.x >= et.x && this.x <= et.x+60 && this.y >= et.y && this.y <= et.y+60) {
                                return true;
                            }

                            if(this.x+20 >= et.x && this.x+20 <= et.x+60 && this.y >= et.y && this.y <= et.y+60) {
                                return true;
                            }

                        }
                        if(et.direct == 2 || et.direct == 3) {
                                if(this.x >= et.x && this.x <= et.x+60 && this.y >= et.y && this.y <= et.y+60) {
                                    return true;
                                }
                                if(this.x+20 >= et.x && this.x+20 <= et.x+60 && this.y >= et.y && this.y <= et.y+60) {
                                    return true;
                                }

                            }
                        }


                }


                break;

            case 1://坦克在向xia移动
                //取出所有的敌人坦克
                for(int i = 0; i < ets.size(); i++) {
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //！！我没有想到的点： 不能自己和自己比较！！
                    if(et != this) {
                        //如果敌人的方向 是向下或者向上
                        if (et.direct == 0 || et.direct == 1) {

                            //this坦克 方向向上 保证他的上面两个点 不会进入别的坦克中
                            //以下两种情况this进入了别的坦克
                            if(this.x >= et.x && this.x <= et.x+60 && this.y+30 >= et.y && this.y+30 <= et.y+60) {
                                return true;
                            }
                            if(this.x+20 >= et.x && this.x+20 <= et.x+60 && this.y+30 >= et.y && this.y+30 <= et.y+60) {
                                return true;
                            }


                        }
                        if(et.direct == 2 || et.direct == 3) {
                                if(this.x >= et.x && this.x <= et.x+60 && this.y+30 >= et.y && this.y+30 <= et.y+60) {
                                    return true;
                                }
                                if(this.x+20 >= et.x && this.x+20 <= et.x+60 && this.y+30 >= et.y && this.y+30 <= et.y+60) {
                                    return true;
                                }

                            }
                        }


                }
                break;

            case 2://坦克在向zuo移动
                //取出所有的敌人坦克
                for(int i = 0; i < ets.size(); i++) {
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //！！我没有想到的点： 不能自己和自己比较！！
                    if(et != this) {
                        //如果敌人的方向 是向下或者向上
                        if (et.direct == 0 || et.direct == 1) {

                            //this坦克 方向向上 保证他的上面两个点 不会进入别的坦克中
                            //以下两种情况this进入了别的坦克
                            if(this.x >= et.x && this.x <= et.x+20 && this.y >= et.y && this.y <= et.y+30) {
                                return true;
                            }
                            if(this.x >= et.x && this.x <= et.x+20 && this.y+20 >= et.y && this.y+20 <= et.y+30) {
                                return true;
                            }

                        }
                        if(et.direct == 2 || et.direct == 3) {
                                if(this.x >= et.x && this.x <= et.x+30 && this.y >= et.y && this.y <= et.y+20) {
                                    return true;
                                }
                                if(this.x >= et.x && this.x <= et.x+30 && this.y+20 >= et.y && this.y+20 <= et.y+20) {
                                    return true;
                                }

                            }
                        }


                }
                break;

            case 3://坦克在向you移动
                //取出所有的敌人坦克
                for(int i = 0; i < ets.size(); i++) {
                    //取出第一个坦克
                    EnemyTank et = ets.get(i);
                    //！！我没有想到的点： 不能自己和自己比较！！
                    if(et != this) {
                        //如果敌人的方向 是向下或者向上
                        if (et.direct == 0 || et.direct == 1) {

                            //this坦克 方向向上 保证他的上面两个点 不会进入别的坦克中
                            //以下两种情况this进入了别的坦克
                            if(this.x+30 >= et.x && this.x+30 <= et.x+20 && this.y >= et.y && this.y <= et.y+30) {
                                return true;
                            }
                            if(this.x+30 >= et.x && this.x+30 <= et.x+20 && this.y+20 >= et.y && this.y+20 <= et.y+30) {
                                return true;
                            }

                        }
                        if(et.direct == 2 || et.direct == 3) {
                                if(this.x+30 >= et.x && this.x+30 <= et.x+30 && this.y >= et.y && this.y <= et.y+20) {
                                    return true;
                                }
                                if(this.x+30 >= et.x && this.x+30 <= et.x+30 && this.y+20 >= et.y && this.y+20 <= et.y+20) {
                                    return true;
                                }

                            }
                        }


                }
                break;
        }



        return b;
    }

    @Override
    public void run() {

        while(true) {

                switch (this.direct) {

                    case 0://坦克在向上移动
                        for(int i =0; i < (int) (Math.random() * 90); i++) {

                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (y > 0 && !this.isTouchOtherEnemy()) {
                                y -= speed;
                            }

                        }
                        break;

                    case 1://坦克在向xia移动
                        for(int i =0; i < (int) (Math.random() * 90); i++) {

                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (y < 270 && !this.isTouchOtherEnemy()) {
                                y += speed;
                            }

                        }
                        break;

                    case 2://坦克在向zuo移动
                            //保证坦克不出边界
                        for(int i =0; i < (int) (Math.random() * 90); i++) {

                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (x < 370 && !this.isTouchOtherEnemy()) {
                                x += speed;
                            }

                        }
                        break;

                    case 3://坦克在向you移动
                        //让坦克平滑的改变方向
                        for(int i =0; i < (int) (Math.random() * 90); i++) {
//(int) (Math.random() * 90)
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if (x > 0 && !this.isTouchOtherEnemy()) {
                                x -= speed;
                            }

                        }
                        break;

            }

            this.times++;

            if(times %2 == 0) {
                if(isAlive) {
                    if(ss.size() < 3) {
                        Shot shot = null;
                        //没有子弹了
                        //添加子弹
                        switch(direct) {
                            case 0:
                                //创建一颗子弹
                                shot = new Shot(x+10,y,0);
                                //把子弹加入到向量里
                                ss.add(shot);
                                break;

                            case 1:
                                //创建一颗子弹
                                shot = new Shot(x+10,y+30,1);
                                //把子弹加入到向量里
                                ss.add(shot);
                                break;


                            case 2:
                                //创建一颗子弹
                                shot = new Shot(x,y+10,2);
                                //把子弹加入到向量里
                                ss.add(shot);
                                break;


                            case 3:
                                //创建一颗子弹
                                shot = new Shot(x+30,y+10,3);
                                //把子弹加入到向量里
                                ss.add(shot);
                                break;

                        }
                        //启动子弹线程
                        Thread t = new Thread(shot);
                        t.start();
                    }
                }
            }

            //方向随机变化
            //让坦克随机产生一个新的方向
            //可以通过让敌人坦克的方向永远指向hero坦克的方向以达到增加游戏难度的目的
            this.direct = (int)(Math.random() * 4);

            //判断敌人坦克是否死亡
            if(this.isAlive == false) {
                //让坦克死亡之后退出线程
                //否则会形成僵尸进程--这个敌人坦克虽然消失了，但是他的线程永远活着
                break;
            }

        }

    }
}

