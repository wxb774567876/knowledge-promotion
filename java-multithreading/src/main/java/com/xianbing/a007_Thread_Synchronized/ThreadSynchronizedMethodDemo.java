package com.xianbing.a007_Thread_Synchronized;

class MyThread3 implements Runnable {
    private int ticketCount = 10;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            saleTicket();
            System.out.println(Thread.currentThread().getName() + "，index=" + i);
        }
    }

    public synchronized void saleTicket() {
        if (ticketCount > 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketCount--;
            System.out.println(Thread.currentThread().getName() + "运行，还剩下" + ticketCount + "张票；");
        } else {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
public class ThreadSynchronizedMethodDemo {
    public static void main(String[] args) {
        MyThread3 myThread3 = new MyThread3();
        Thread thread1 = new Thread(myThread3);
        Thread thread2 = new Thread(myThread3);
        Thread thread3 = new Thread(myThread3);
        thread1.start();
        thread2.start();
        thread3.start();
        //以下是模拟不同线程对象同时运行的效果，发现不会两个子线程互相不受影响
        /*MyThread3 myThread = new MyThread3();
        Thread thread1 = new Thread(myThread3);
        Thread thread2 = new Thread(myThread);
        thread1.start();
        thread2.start();*/
    }
}
