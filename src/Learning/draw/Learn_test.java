package Learning.draw;
//仅仅是学习绘图类而已，不属于项目内容
import javax.swing.*;
import java.awt.*;

public class Learn_test extends JFrame {
    private Mypanel mp =null;
    public static void main(String[] args) {
        new Learn_test();
    }
    public Learn_test() {
        mp = new Mypanel();
        this.add(mp);
        this.setSize(400, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//这个用来关闭窗口后就退出程序
    }
}
class Mypanel extends JPanel {
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawOval(100, 100, 100, 100);
        g.drawLine(100, 100, 100, 200);
        g.drawLine(100,100,200,100);
        g.drawRect(0,0,100,100);
        g.fillOval(100,100,100,100);
        g.setColor(Color.green);
        g.fillRect(0,0,100,100);
        g.setColor(Color.red);
        g.setFont(new Font("Times New Roman",Font.BOLD,14));
        g.drawString("Hello World",300,300);
        //Draw a picture
        //                Image imgae = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/res/1.png"));
        //                g.drawImage(imgae,200,400,this);//有九个报错！！！
    }
}
