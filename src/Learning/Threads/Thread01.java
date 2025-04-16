package Learning.Threads;

public class Thread01 {
    public static void main(String[] args) {
        T t = new T();
        t.start();
        T1 t1 = new T1();
        t1.start();
    }
}

class T extends Thread {
    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println("@");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i == 10) {
                break;
            }
        }
    }
}

class T1 extends Thread {
    @Override
    public void run() {
        int i = 0;
        while (true) {
            System.out.println("#");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            if (i == 10) {
                break;
            }
        }
    }
}