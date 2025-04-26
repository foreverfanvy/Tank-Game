package Tank_Res;

import java.util.Vector;


public class EnemyTank extends Tank {
    public Vector<Shot> shots = new Vector<>();
    public boolean isLive = true;
    public int type = 1;
    //创建一个表来存子弹

    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
