package com.xianbing.a003_Thread_Sleep;
class Mythread implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i > 5) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
        }
    }
}
public class ThreadSleepDemo {
    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        Thread thread = new Thread(mythread, "线程A");
        thread.start();
    }
}
