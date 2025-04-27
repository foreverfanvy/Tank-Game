package Tank_Res;

public class Shot implements Runnable {
    int x;
    int y;
    int direction = 0;
    int speed = 2;
    boolean isLive = true;
    int index = 0;

    public Shot(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }//构造一个射击的行为

    //run方法是一个射击行为（不断改变坐标）
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.printStackTrace();
            }
            switch (direction) {
                case 0://上
                    y -= speed;
                    break;
                case 1://右
                    x += speed;
                    break;
                case 2://下
                    y += speed;
                    break;
                case 3://左
                    x -= speed;
                    break;
            }
//            测试输出功能
//            System.out.println("x: " + x + " y: " + y);
            //当子弹碰到别的tankor边界就进行退出
            if (!(x >= 0 && x <= 1000 && y >= 0 && y <= 750 && isLive)) {
                isLive = false;
                break;
            }

        }
    }
}

