package com.xianbing.a002_Thread_Join;

/**
 * 测试主线程等待所有子线程执行完最终处理
 */
class CustomThread implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
        }
    }
}
public class ThreadJoinDemo2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new CustomThread(), "线程A");
        Thread thread2 = new Thread(new CustomThread(), "线程B");
        Thread thread3 = new Thread(new CustomThread(), "线程C");

        thread1.start();
        thread2.start();
        thread3.start();
        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行结束！！");
    }
}
