/******************************************************************************
 * Copyright (C) ShenZhen Powerdata Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市博安达信息技术股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.xianbing.a002_Thread_Join.test;
class Mythred implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(5000);
            EvalData.eval();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("子线程执行结束");
    }

}

class EvalData{
    public synchronized static void eval() {
        System.out.println("执行数据评价");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


public class Demo {

    public static String test() {
        return "结束";
    }

    public static void main(String[] args) throws InterruptedException {
        Mythred mythred = new Mythred();
        Thread thread1 = new Thread(mythred);
        Thread thread2 = new Thread(mythred);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(test());
    }

}
