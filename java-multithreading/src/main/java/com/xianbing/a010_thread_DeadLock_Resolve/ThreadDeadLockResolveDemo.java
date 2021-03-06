/******************************************************************************
 * Copyright (C) ShenZhen Powerdata Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市博安达信息技术股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.xianbing.a010_thread_DeadLock_Resolve;
class MyThread1 implements Runnable {
    private Object objA = null;

    private Object objB = null;

    public MyThread1(Object objA, Object objB) {
        this.objA = objA;
        this.objB = objB;
    }

    @Override
    public void run() {
        synchronized (objA) {
            say();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objB) {
                get();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //线程222释放了objB的锁，并进入等待池，线程111执行完得唤醒线程222继续执行
                objB.notify();
            }
        }
    }

    public void say() {
        System.out.println("线程111说，你给我对象B,我给你对象A");
    }

    public void get() {
        System.out.println("线程111得到对象B；");
    }
}

class MyThread2 implements Runnable {
    private Object objA = null;

    private Object objB = null;

    public MyThread2(Object objA, Object objB) {
        this.objA = objA;
        this.objB = objB;
    }

    @Override
    public void run() {
        synchronized (objB) {
                say();
            try {
                Thread.sleep(5000);
                //线程222休息了5秒钟，为了防止死锁发生，我释放objB对象锁，让线程111先执行吧，我进入等待池；
                objB.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (objA) {
                get();
            }
        }
    }

    public void say() {
        System.out.println("线程222说，你给我对象A,我给你对象B");
    }

    public void get() {
        System.out.println("线程222得到对象A；");
    }
}

/**
 * 死锁案例
 * @company    深圳市博安达信息技术股份有限公司
 * @author     王贤炳
 * @since      2018/7/29
 */
public class ThreadDeadLockResolveDemo {
    public static void main(String[] args) {
        Object objA = new Object();
        Object objB = new Object();
        MyThread1 myThread1 = new MyThread1(objA, objB);
        MyThread2 myThread2 = new MyThread2(objA, objB);
        Thread threadA = new Thread(myThread1, "线程111");
        Thread threadB = new Thread(myThread2, "线程222");
        threadA.start();
        threadB.start();
    }
}
