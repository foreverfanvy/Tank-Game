package Learning.Threads;

public class Thread03 {
    public static void main(String[] args) {
        int remain = 10000;
        Card card = new Card(remain);
        Thread t1 = new Thread(card);
        t1.setName("Thread 1");
        Thread t2 = new Thread(card);
        t2.setName("Thread 2");
        t1.start();
        t2.start();
    }
}
class Card extends Thread {
    boolean loop = true;
    int remaining;
    public Card(int remain) {
        this.remaining = remain;
    }
    public synchronized void run() {
        while (loop) {
//            synchronized (this) {
//                remaining -= 1000;
//             System.out.println(Thread.currentThread().getName()
//            + "-100"+"remaining:" + remaining);
//                if (remaining <= 0) {
//                    loop = false;
//                }
//            }
            remaining -= 1000;
                System.out.println(Thread.currentThread().getName()
                        + "-100"+"remaining:" + remaining);
                if (remaining <= 0) {
                    loop = false;
                }
            try {
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}

