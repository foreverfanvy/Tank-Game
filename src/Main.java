import Tank_Res.MyPanel;

import javax.swing.*;

//This is the main fuction to start this game
public class Main extends JFrame {
    MyPanel panel = null;

    public Main() {
        panel = new MyPanel();
        new Thread(panel).start();//这里启动了关于子弹绘画更新的渲染线程，姑且称为线程1
        this.add(panel);
        this.setSize(1000, 750);
        this.setVisible(true);
        this.addKeyListener(panel);//JFame来监听画板
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Main();
    }
}