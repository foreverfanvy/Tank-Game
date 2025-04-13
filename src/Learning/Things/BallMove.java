package Learning.Things;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//实现小球用键盘来进行上下左右的移动
public class BallMove extends JFrame {
    MYPanel panel;

    public static void main(String[] args) {
        new BallMove();
    }

    public BallMove() {
        panel = new MYPanel();
        this.add(panel);
        this.setTitle("Ball Move");
        this.setSize(800, 600);
        this.addKeyListener(panel);//面板没有监听功能
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MYPanel extends JPanel implements KeyListener {
    int x = 10, y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }//监听字符输出

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println((char)e.getKeyCode() +"Key pressed");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                y--;
                break;
            case KeyEvent.VK_LEFT:
                x--;
                break;
            case KeyEvent.VK_RIGHT:
                x++;
                break;
            case KeyEvent.VK_DOWN:
                y++;
                break;
        }//这个要上下左右键，而WSAD要用if来写
        repaint();
    }//当某个键被按压的时候会自动触发

    @Override
    public void keyReleased(KeyEvent e) {

    }//当某个键松开了才会触发
}