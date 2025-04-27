package Tank_Res;

public class Boom {
    int x, y;
    int life = 9;//设置生命周期是9
    boolean isLive = true;

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //life越小显示的图片就越小，这就是实现爆炸效果的基本逻辑
    public void lifeDowm() {
        if (life > 0) {
            life--;
        } else {
            isLive = false;
        }
    }
    //炸弹内容是panel中的内容
}
