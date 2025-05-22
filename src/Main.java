import Tank_Res.MyPanel;
import Tank_Res.Recorder;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("all")
//This is the main fuction to start this game
public class Main extends JFrame {
    MyPanel panel = null;

    public Main() {
        panel = new MyPanel();
        new Thread(panel).start();//这里启动了关于子弹绘画更新的渲染线程，姑且称为线程1
        this.add(panel);
        this.setSize(1300, 750);
        this.setVisible(true);
        this.addKeyListener(panel);//JFame来监听画板
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //在Jfram中增加响应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("正在保存退出");
                Recorder.keep();
                System.exit(0);
            }
        });
    }


    public static void main(String[] args) {
        new Main();
    }
}