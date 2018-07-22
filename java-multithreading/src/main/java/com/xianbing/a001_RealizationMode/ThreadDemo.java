package com.xianbing.a001_RealizationMode;

class CustomThread extends Thread {
    private String threadName;

    CustomThread(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(threadName + "运行，i=" + i);
            //测试如何取得当前运行线程的名称,如果未指定线程名称，系统会自动设置线程名称
            //System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
        }
    }
}


public class ThreadDemo{

    public static void main(String[] args) {
        CustomThread customThread1 = new CustomThread("线程A");
        CustomThread customThread2 = new CustomThread("线程B");
        CustomThread customThread3 = new CustomThread("线程C");
        customThread3.setName("真正的线程名称");
        customThread1.start();
        customThread2.start();
        customThread3.start();
    }

}
