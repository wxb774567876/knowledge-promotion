package com.xianbing.a007_Thread_Synchronized;

class MyThread2 implements Runnable {
    private int ticketCount = 10;
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            saleTicket();
            System.out.println(Thread.currentThread().getName() + ",index=" + i);
        }
    }

    public void saleTicket() {
        synchronized (this) {
            if (ticketCount > 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticketCount--;
                System.out.println(Thread.currentThread().getName() + "运行，还剩下" + ticketCount + "张票");
            }
        }
    }
}
public class threadSynchronizedObjectDemo {
    public static void main(String[] args) {
        MyThread2 myThread2 = new MyThread2();
        Thread thread1 = new Thread(myThread2);
        Thread thread2 = new Thread(myThread2);
        Thread thread3 = new Thread(myThread2);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
