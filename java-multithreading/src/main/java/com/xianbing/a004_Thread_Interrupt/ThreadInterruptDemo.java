package com.xianbing.a004_Thread_Interrupt;

class Mythred implements Runnable {

    @Override
    public void run() {
        System.out.println("进入run方法");

        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                try {
                    Thread.sleep(5000);
                    System.out.println(Thread.currentThread().getName() + "休眠正常结束。");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "休眠被意外终止！！！");
                    return;
                }
            }
        }
        System.out.println("run方法正常结束");
    }
}

public class ThreadInterruptDemo {
    public static void main(String[] args) {
        Mythred mythred = new Mythred();
        Thread thread = new Thread(mythred, "线程A");
        thread.start();
        //主线程休息2秒
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            System.out.println("主线程休眠被意外终止了");
        }
        thread.interrupt();
        System.out.println("主线程正常执行结束。");
    }
}
