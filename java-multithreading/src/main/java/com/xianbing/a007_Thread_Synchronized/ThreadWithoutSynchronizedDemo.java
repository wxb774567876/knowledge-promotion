package com.xianbing.a007_Thread_Synchronized;

class MyThread implements Runnable {

    private int ticketCount = 10;
    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            saleTicket();
        }
    }

    public void saleTicket() {
        if (ticketCount > 0) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticketCount--;
            System.out.println(Thread.currentThread().getName() + "运行，还剩下" + ticketCount + "张票没有卖出去");
        }
    }
}

public class ThreadWithoutSynchronizedDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread threadA = new Thread(myThread, "线程A");
        Thread threadB = new Thread(myThread, "线程B");
        Thread threadC = new Thread(myThread, "线程C");
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
