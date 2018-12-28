package com.xianbing.a005_Thread_Priority;

class MyThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
        }
    }
}
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        //MyThread myThread = new MyThread();
        Thread thread1 = new Thread(new MyThread(), "线程A");
        Thread thread2 = new Thread(new MyThread(), "线程B");
        Thread thread3 = new Thread(new MyThread(), "线程C");
        thread1.setPriority(Thread.MAX_PRIORITY);
        thread2.setPriority(Thread.MIN_PRIORITY);
        thread3.setPriority(Thread.MIN_PRIORITY);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
