package Tank_Res;

import Tank_Res.son_Tank.Son1_Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyPanel extends JPanel implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        son1_Tank.setTpye(0);
        if (e.getKeyCode() == KeyEvent.VK_W) {
            son1_Tank.setDirection(0);
            son1_Tank.setY(son1_Tank.getY() - son1_Tank.getSpeed());
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            son1_Tank.setDirection(2);
            son1_Tank.setY(son1_Tank.getY() + son1_Tank.getSpeed());
        }
        else if (e.getKeyCode() == KeyEvent.VK_A) {
            son1_Tank.setDirection(3);
            son1_Tank.setX(son1_Tank.getX() - son1_Tank.getSpeed());
        }
        else if (e.getKeyCode() == KeyEvent.VK_D) {
            son1_Tank.setDirection(1);
            son1_Tank.setX(son1_Tank.getX() + son1_Tank.getSpeed());
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    Son1_Tank son1_Tank = null;
    public MyPanel() {
        son1_Tank = new Son1_Tank(100, 100);
        son1_Tank.setSpeed(2);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        //        draw_Tank(0,0,1,1,g);
        //        draw_Tank(100,100,2,0,g);
        //        draw_Tank(200,200,3,1,g);
        //        draw_Tank(300,300,0,0,g);
        //功能测试
        draw_Tank(son1_Tank.getX(),son1_Tank.getY(),son1_Tank.getDirection(),son1_Tank.getTpye(),g);
    }

    /**
     *
     * @param x:Tank左上角的坐标X
     * @param y：Tank左上角的坐标Y
     * @param direction：Tank的枪管的朝向，只有四个选项
     * @param g：画笔
     * @param type ：敌我双方，用颜色区分
     */
    public void draw_Tank(int x,int y,int direction,int type,Graphics g) {
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
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.fill3DRect(x+30,y,10,60,false);
                g.drawLine(x+20,y+30,x+20,y);
                break;
            case 1://1表示向右
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x+60,y+20);
                break;
            case 2://2表示向下
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x+10,y+10,20,40,false);
                g.fillOval(x+10,y+20,20,20);
                g.fill3DRect(x+30,y,10,60,false);
                g.drawLine(x+20,y+30,x+20,y+60);
                break;
            case 3://3表示向左
                g.fill3DRect(x,y,60,10,false);
                g.fill3DRect(x+10,y+10,40,20,false);
                g.fill3DRect(x,y+30,60,10,false);
                g.fillOval(x+20,y+10,20,20);
                g.drawLine(x+30,y+20,x,y+20);
                break;
        }
    }
}