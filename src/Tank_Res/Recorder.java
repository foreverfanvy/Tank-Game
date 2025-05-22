package Tank_Res;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.Buffer;

public class Recorder {
    //用来进行成绩记录的类
    private static int dieEnemyTankNum = 0;
    //创建IO流
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    //定义记录文件的路径
    private static String recordFile = "src/res/record.txt";
    //这里注意两个平台的路径表示方法是不一样的
    //win使用的是反斜杠方式，Linux使用正斜杠方式

    public static int getDieEnemyTankNum() {
        return dieEnemyTankNum;
    }
    public static void keep() {
        try {
            fw = new FileWriter(recordFile);
            bw = new BufferedWriter(fw);
            bw.write(dieEnemyTankNum+"\r\n");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(bw!=null)
                try {
                    bw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    public static void setDieEnemyTankNum(int dieEnemyTankNum) {
        Recorder.dieEnemyTankNum = dieEnemyTankNum;
    }

    public static void add_num(){
        Recorder.dieEnemyTankNum++;
    }

}
