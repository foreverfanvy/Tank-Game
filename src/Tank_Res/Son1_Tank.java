package Tank_Res;

public class Son1_Tank extends Tank {
    //表示对应tank的射击行为
    public Shot shot = null;

    public Son1_Tank(int x, int y) {
        super(x, y);
    }

    public void shotting() {
        //先创建一个子弹，需要关于Tank的方向信息
        switch (getDirection()) {
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 60, getY() + 20, 1);
                break;
            case 2:
                shot = new Shot(getX() + 20, getY() + 60, 2);
                break;
            case 3:
                shot = new Shot(getX(), getY() + 20, 3);
                break;
        }
        new Thread(shot).start();//启动子弹的移动线程，姑且称为线程2

    }

}
