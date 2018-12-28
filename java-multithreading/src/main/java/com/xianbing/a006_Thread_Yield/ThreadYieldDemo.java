package com.xianbing.a006_Thread_Yield;

class MyThread implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(threadName + "运行，i=" + i);
            if (threadName.equals("线程A") && i == 300) {
                System.out.print(threadName + "礼让，");
                Thread.yield();
            }
            if (threadName.equals("线程B") && i == 7) {
                System.out.print(threadName + "礼让，");
                Thread.yield();
            }
        }


    }
}
public class ThreadYieldDemo {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyThread(), "线程A");
        Thread thread2 = new Thread(new MyThread(), "线程B");
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
    }
}
