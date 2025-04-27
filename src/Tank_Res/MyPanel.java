package Tank_Res;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.Vector;

@SuppressWarnings("all")
public class MyPanel extends JPanel implements KeyListener, Runnable {
    int EnemySize = 3;
    Son1_Tank son1_Tank = null;
    Vector<EnemyTank> enemyTankVector = new Vector();
    Vector<Boom> boomVector = new Vector();

    //创建三张图片来显示爆炸效果
    Image image1;
    Image image2;
    Image image3;

    public MyPanel() {
        //创建自己坦克的位置
        son1_Tank = new Son1_Tank(100, 100);
        son1_Tank.setSpeed(2);
        //创建敌人的初始坦克
        for (int i = 0; i < EnemySize; i++) {
            EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
            enemyTank.setDirection(2);
            enemyTankVector.add(enemyTank);
            // 装填子弹
            Shot s = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDirection());
            enemyTank.shots.add(s);
            new Thread(s).start();//启动敌人的子弹的线程，姑且成为3号线程
        }
        //再构造器里面初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/bomb_3.gif"));
        //修复bug1
        Toolkit.getDefaultToolkit().prepareImage(image1, -1, -1, null);   // 同步等图像解码
//        目前你的流程是：
//        image1 = Toolkit.getDefaultToolkit().getImage(...); ← 异步加载
//        第一次命中 → boomVector.add(...)
//        立即 repaint() → g.drawImage(image1, …） GIF 还没解码完，drawImage 其实什么都没画
//        你马上 boom.life--，第一颗 Boom 很快就被移除了
//        所以首炸看不到。
    }

    //用来判断子弹是否击中目标的函数
    //判断什么时候击中？必须一直循环着判断（run方法中进行判断）
    public void HitTank(Shot s, EnemyTank enemyTank) {
        //判断集中坦克没有，就是s.x和tank.x等属性的比较
        switch (enemyTank.getDirection()) {
            case 0:
            case 2:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 40
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 60){
                    s.isLive = false;
                    //这个可以让敌人的坦克死亡
                    enemyTank.isLive = false;
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    boomVector.add(boom);
                }//这个少了个大括号导致出现了一堆bug我去
                    break;
            case 1:
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60
                        && s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40){
                    s.isLive = false;
                    enemyTank.isLive = false;
                    Boom boom = new Boom(enemyTank.getX(), enemyTank.getY());
                    boomVector.add(boom);
                }
                    break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        son1_Tank.setTpye(0);
        if (e.getKeyCode() == KeyEvent.VK_W) {
            son1_Tank.setDirection(0);
            son1_Tank.setY(son1_Tank.getY() - son1_Tank.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            son1_Tank.setDirection(2);
            son1_Tank.setY(son1_Tank.getY() + son1_Tank.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            son1_Tank.setDirection(3);
            son1_Tank.setX(son1_Tank.getX() - son1_Tank.getSpeed());
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            son1_Tank.setDirection(1);
            son1_Tank.setX(son1_Tank.getX() + son1_Tank.getSpeed());
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            son1_Tank.shotting();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);
        //画出自己的坦克（自己的Tank是Son1_Tank）
        draw_Tank(son1_Tank.getX(), son1_Tank.getY(), son1_Tank.getDirection(),
                son1_Tank.getTpye(), g);
        //有关敌人坦克的绘制
        Iterator<EnemyTank> it = enemyTankVector.iterator();
        while (it.hasNext()) {
            EnemyTank enemyTank = it.next();
            if (enemyTank.isLive) {
                // 敌人还活着，就画出来
                draw_Tank(enemyTank.getX(), enemyTank.getY(), enemyTank.getDirection(), 1, g);

                // 画敌人子弹，也用 Iterator
                Iterator<Shot> shotIt = enemyTank.shots.iterator();
                while (shotIt.hasNext()) {
                    Shot shot = shotIt.next();
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        shotIt.remove(); // 子弹死了，安全删除
                    }
                }
            } else {
                // 如果敌人已经死了，从 Vector 中安全删除
                it.remove();
            }
        }for (int i = 0; i < enemyTankVector.size(); i++) {
            EnemyTank enemyTank = enemyTankVector.get(i);
            //当前tank存活才会进行绘制
            if (enemyTank.isLive == true) {
                draw_Tank(enemyTank.getX(), enemyTank.getY(), enemyTank.getDirection()
                        , 1, g);
                //存活才会绘制敌人tank的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive == true) {
                        g.draw3DRect(shot.x, shot.y, 2, 2, false);
                    } else {
                        //Vector中子弹死掉了后要移除，不然一直画死的子弹但是又画不出来导致卡死，关键点！！！
                        enemyTank.shots.remove(j);
                    }
                }
            }
            if(enemyTank.isLive == false) {
                enemyTankVector.remove(enemyTank);
            }
        }
        //画出自己的子弹
        if ((son1_Tank.shot != null) && (son1_Tank.shot.isLive == true)) {
            g.setColor(Color.red);
            g.draw3DRect(son1_Tank.shot.x, son1_Tank.shot.y, 2, 2, false);
            //            System.out.println("子弹被绘制！");(日志测试代码)
        }
        //boomVector中有炸弹就要进行绘制特效
        if(boomVector.size() != 0){
            for (int i = 0; i < boomVector.size(); i++) {
                Boom boom = boomVector.get(i);
                if (boom.life > 6 ) {
                    g.drawImage(image1,boom.x, boom.y, 60,60,this);
                }
                else if(boom.life > 3){
                    g.drawImage(image2,boom.x, boom.y, 60,60,this);
                }
                else if(boom.life > 0){
                    g.drawImage(image3,boom.x, boom.y, 60,60,this);
                }
                boom.lifeDowm();//为了实现不断变小的效果，不然仅仅就是独立的三次展示不够明显和自然
                if(boom.life == 0){
                    boomVector.remove(boom);
                    i--;//删除了这个元素，后续会出现补位的现象，所以需要倒回去重新实现一下
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //判断敌人是否被击中
            if (son1_Tank.shot != null && son1_Tank.shot.isLive == true) {
                //此处会有异常出现（每点击J的时候son1_Tank.shot才不为空）
                //自己的子弹还活着的时候
                for (int i = 0; i < enemyTankVector.size(); i++) {
                    //遍历敌人所有的tank，取出tank来进行Tank判断是否死亡
                    EnemyTank enemyTank = enemyTankVector.get(i);
                    HitTank(son1_Tank.shot, enemyTank);
                    //判断敌人的Tank是不是被hit了,击中了就死掉
                }
            }
            this.repaint();
        }
    }//不断的刷新绘图区域

    /**
     * @param x:Tank左上角的坐标X
     * @param y：Tank左上角的坐标Y
     * @param direction：Tank的枪管的朝向，只有四个选项
     * @param g：画笔
     * @param type                        ：敌我双方，用颜色区分
     */
    public void draw_Tank(int x, int y, int direction, int type, Graphics g) {
        switch (type) {
            case 0://我们的坦克，普通的青色
                g.setColor(Color.cyan);
                break;
            case 1://敌人的坦克，黄色
                g.setColor(Color.yellow);
                break;
        }
        switch (direction) {
            case 0://0表示朝上
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1://1表示向右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2://2表示向下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3://3表示向左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
        }
    }
}
