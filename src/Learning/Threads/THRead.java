package Learning.Threads;

public class THRead {
    public static void main(String[] args) throws InterruptedException {
        Cat cat = new Cat();
        cat.start();//启动线程,不会阻塞main线程
        while (cat.num<=10) {
            System.out.println(Thread.currentThread().getName()+"is running");
            Thread.sleep(1000);
        }
        Cat1 cat1 = new Cat1();
        //cat1.start();//不能直接start
        Thread thread = new Thread();
        thread.start();
    }
}

class Cat1 implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " is running");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}

class Cat extends Thread {
    public int num=0;
    @Override
    public void run() {
        //实现自己的业务逻辑
        System.out.println(Thread.currentThread().getName() + " is running");
        while (true) {
            System.out.println("喵！");
            try {
                Thread.sleep(1000);//单位是ms，所以1000ms = 1s
            }catch (InterruptedException e) {
            }//必须try-catch否则有报错
            num++;
            if (num==1000) {
                break;
            }
        }
    }
}