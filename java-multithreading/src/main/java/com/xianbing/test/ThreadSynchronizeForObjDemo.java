/******************************************************************************
 * Copyright (C) ShenZhen Powerdata Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市博安达信息技术股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.xianbing.test;

import com.xianbing.App;

class MyThread implements Runnable {
    private int total = 50;//仓库能存储的最大个数
    private int residualNumber;//仓库剩余的个数
    private App app = new App();
    @Override
    public void run() {

    }

    public void consumer(int connsumptionNumber) {
        synchronized (app) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (connsumptionNumber > residualNumber) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notify();
            } else {
                System.out.println("消费者消费了" + connsumptionNumber + "个产品");
                residualNumber -= connsumptionNumber;
            }
        }
    }

    public void producer() {
        synchronized (app) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (residualNumber < total) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int produceCount = total - residualNumber;
                System.out.println("生产者生成了" + produceCount + "个产品");
                residualNumber = total;
            } else {
                notify();
            }
        }
    }


}
public class ThreadSynchronizeForObjDemo {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread producer = new Thread(myThread);
        Thread consumer1 = new Thread(myThread);
        Thread consumer2 = new Thread(myThread);
        Thread consumer3 = new Thread(myThread);

    }
}
