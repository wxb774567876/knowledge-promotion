package com.xianbing.a001_RealizationMode;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyCallable implements Callable<Integer> {
    private int i = 0;
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (; i < 12; i++) {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + "运行，i=" + i);
            sum += i;
        }
        return sum;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        Callable<Integer> myCallable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(myCallable);
        Thread thread = new Thread(futureTask, "子线程");
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "线程运行，i=" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 3) {
                thread.start();
            }
        }
        System.out.println("主线程运行完毕");
        try {
            int sum = futureTask.get();
            System.out.println("sum=" + sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
