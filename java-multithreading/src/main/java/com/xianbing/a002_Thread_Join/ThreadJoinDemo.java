package com.xianbing.a002_Thread_Join;
class MyThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
        }
    }
}
public class ThreadJoinDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread thread = new Thread(myThread, "新建线程");
        Thread threadB = new Thread(myThread, "线程B");
        thread.start();
        threadB.start();
        for (int i = 0; i < 10000; i++) {
            if (i == 599) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (i == 5) {
                try {
                    threadB.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("主线程运行，i=" + i);
        }
        System.out.println("主线程执行完了！");
    }
}
