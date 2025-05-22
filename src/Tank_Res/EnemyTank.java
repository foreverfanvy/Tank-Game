package Tank_Res;

import java.util.Vector;

@SuppressWarnings("all")
public class EnemyTank extends Tank implements Runnable {
    public Vector<Shot> shots = new Vector<>();
    public Vector<EnemyTank> enemyTanks = new Vector<>();
    public int type = 1;

    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //创建一个表来存子弹
    //用来从mypanel里面的enemyTanks属性设置到EnemyTank这个类中
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //创建一个方法来遍历所有的Vector中的成员，判断tank之间有没有重叠的部分（不合理的游戏设计）
    public boolean isTouch() {
        // 开始分类，4*2=8种情况
        switch (this.getTpye()) { // 注意：此处字段名为 tpye 是拼写错误，但保持原样
            case 0: // 上
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 敌人上下方向 → 判断最上面两个点是否重叠
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60)
                                return true;
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60)
                                return true;
                        }
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 敌人左右方向 → 判断上方两个点是否进入敌方坦克范围
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40)
                                return true;
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40)
                                return true;
                        }
                    }
                }
                break;

            case 1: // 右
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 敌人上下方向 → 判断右侧两个点是否重叠
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60)
                                return true;
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60)
                                return true;
                        }
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 敌人左右方向 → 判断右侧两个点是否进入敌方坦克范围
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40)
                                return true;
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40)
                                return true;
                        }
                    }
                }
                break;

            case 2: // 下
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 敌人上下方向 → 判断下方两个点是否重叠
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60)
                                return true;
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60)
                                return true;
                        }
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 敌人左右方向 → 判断下方两个点是否进入敌方坦克范围
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40)
                                return true;
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40)
                                return true;
                        }
                    }
                }
                break;

            case 3: // 左
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank != this) {
                        if (enemyTank.getDirection() == 0 || enemyTank.getDirection() == 2) {
                            // 敌人上下方向 → 判断左侧两个点是否重叠
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60)
                                return true;
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60)
                                return true;
                        }
                        if (enemyTank.getDirection() == 1 || enemyTank.getDirection() == 3) {
                            // 敌人左右方向 → 判断左侧两个点是否进入敌方坦克范围
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40)
                                return true;
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40)
                                return true;
                        }
                    }
                }
                break;
        }
        return false;
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
                        if (getY() > 0 && !isTouch()) moveUp();
                    }
                    break;
                case 1:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getX() + 60 < 1000 && !isTouch()) moveRight();
                    }
                    break;
                case 2:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getY() + 60 < 750 && !isTouch()) moveDown();
                    }
                    break;
                case 3:
                    for (int i = 0; i < 20; i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (getX() > 0 && !isTouch()) moveLeft();
                    }
                    break;
            }
            setDirection((int) (Math.random() * 4));//随机设置一个方向[0,4)利用int取整
            //退出线程的条件设计！！！（并发程序的关键）
            if (this.isLive == false) break;//被击中->isLive==false就退出循环
            // 判断shots的size个数来进行子弹数量的创建与限制
            Shot s = null;
            if (shots.size() == 1 && isLive) {//这里的size设置的内容是敌人子弹个数的最大值，一般设置一个不然会出现很多子弹导致很乱
                switch (getDirection()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                //
                shots.add(s);
                new Thread(s).start();
            }
        }
    }
}
