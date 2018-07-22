package com.xianbing.a001_RealizationMode;

class MyThread implements Runnable {
    private String threadName;

    MyThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(threadName + "运行，i=" + i);
            //测试如何取得当前运行线程的名称,如果未指定线程名称，系统会自动设置线程名称
            //System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
        }
    }
}

public class RunnableDemo {
    public static void main(String[] args) {
        MyThread myThread1 = new MyThread("线程A");
        MyThread myThread2 = new MyThread("线程B");
        MyThread myThread3 = new MyThread("线程C");
        Thread thread1 = new Thread(myThread1);
        Thread thread2 = new Thread(myThread2);
        Thread thread3 = new Thread(myThread3, "自定义线程名称");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}


