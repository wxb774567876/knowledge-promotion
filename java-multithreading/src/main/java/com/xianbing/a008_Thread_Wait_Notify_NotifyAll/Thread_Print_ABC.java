package com.xianbing.a008_Thread_Wait_Notify_NotifyAll;

class MyThread implements Runnable {
    private String word = null;
    private Object currObj = null;//当前对象
    private Object prevObj = null;//下一个对象

    public MyThread(String word, Object prevObj, Object currObj) {
        this.word = word;
        this.prevObj = prevObj;
        this.currObj = currObj;
    }

    @Override
    public void run() {
        int count = 10;
        //System.out.println(Thread.currentThread().getName() + "执行；");
        while (count > 0) {
            synchronized (prevObj) {
                String threadNamePrev = Thread.currentThread().getName();
                System.out.println("第一层synchronized线程是：【" + threadNamePrev + "】");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (currObj) {
                    String threadNameCurr = Thread.currentThread().getName();
                    System.out.println("第二层synchronized线程是：【" + threadNameCurr  + "】");
                    System.out.println(word);
                    count--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //唤醒等待currObj对象的线程
                    currObj.notify();
                }
                try {
                    //让等待prevObj对象的线程等待
                    prevObj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class Thread_Print_ABC {
    public static void main(String[] args) throws InterruptedException {
        Object objA = new Object();
        Object objB = new Object();
        Object objC = new Object();
        MyThread myThreadA = new MyThread("A", objC, objA);
        MyThread myThreadB = new MyThread("B", objA, objB);
        MyThread myThreadC = new MyThread("C", objB, objC);
        new Thread(myThreadA, "A").start();
        Thread.currentThread().sleep(1000);
        new Thread(myThreadB, "B").start();
        Thread.currentThread().sleep(1000);
        new Thread(myThreadC, "C").start();
    }
}
