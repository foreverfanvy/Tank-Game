package Learning.Threads;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

public class THread02 {
    public static void main(String[] args) {
        num num = new num();
        Q q = new Q(num);
        num.start();
        q.start();
    }
}
class num extends Thread {
    boolean loop = true;
    @Override
    public void run() {
        while (loop) {
            System.out.println((int)(Math.random()*100)+1);
            try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
    }
}

class Q extends Thread {
    private num num;
    public Q(num num) {
        this.num = num;
    }
    private Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        while(true){
            System.out.println("输入Q来退出程序");
            //这里为什么会出现阻塞的现象？？？
            if(scanner.next().charAt(0) == 'Q') {
                num.loop = false;//这个地方由于会有Enter机制的存在导致输入Q后不会马上退出！！！
                System.out.println("Q exit");
                break;
            }
        }

    }
}
