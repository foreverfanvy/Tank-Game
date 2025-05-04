package Tank_Res;

import java.util.Vector;

@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable {
    public Vector<Shot> shots = new Vector<>();
    public int type = 1;
    //创建一个表来存子弹

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {
            switch (getDirection()) {
                case 0:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getY() > 0) moveUp();
                    }
                    break;
                case 1:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getX() + 60 < 1000) moveRight();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getY() + 60 < 750) moveDown();
                    }
                    break;
                case 3:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getX() > 0) moveLeft();
                    }
                    break;
            }
            setDirection((int) (Math.random() * 4));//随机设置一个方向[0,4)利用int取整
            //退出线程的条件设计！！！（并发程序的关键）
            if (this.isLive == false) break;//被击中->isLive==false就退出循环
            // 判断shots的size个数来进行子弹数量的创建与限制
            Shot s = null;
            if(shots.size() == 1  && isLive) {//这里的size设置的内容是敌人子弹个数的最大值，一般设置一个不然会出现很多子弹导致很乱
                switch (getDirection()) {
                    case 0:
                        s= new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s = new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s = new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s = new Shot(getX(),getY()+20,3);
                        break;
                }
                //
                shots.add(s);
                new Thread(s).start();
            }
        }
    }
}
