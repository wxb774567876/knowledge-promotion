/******************************************************************************
 * Copyright (C) ShenZhen Powerdata Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市博安达信息技术股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.xianbing.a004_Thread_Interrupt_chongqing_amend.plan;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程实现类
 *
 * @author:     王贤炳
 * @since:      JDK1.8
 * @history:    2021/4/18
 */
class Mythred implements Runnable {

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        System.out.println("【" + threadName + "】进入run方法，实际各线程进入评价的时间点不同");
//        DataReport dataReport = new DataReport();
        if ("线程A".equals(threadName)) {
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
//            EvalData.surfaceWaterEvalution("河流");
            try {
                DataReport dataReport = new DataReport();
                dataReport.reportDataStorage("河流", null, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if ("线程B".equals(threadName)) {
            /*try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
//            EvalData.surfaceWaterEvalution("湖库");
            try {
                DataReport dataReport = new DataReport();
                dataReport.reportDataStorage("湖库", null, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if ("线程C".equals(threadName)) {
            /*try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
//            EvalData.surfaceWaterEvalution("采测分离");
            try {
                DataReport dataReport = new DataReport();
                dataReport.reportDataStorage("采测分离", null, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else if ("线程D".equals(threadName)) {
            /*try {
                Thread.sleep(13000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
//            EvalData.surfaceWaterEvalution("饮用水");
            try {
                DataReport dataReport = new DataReport();
                dataReport.reportDataStorage("饮用水", null, null);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("【" + threadName + "】结束run方法");
    }
}

/**
 * 评价类
 *
 * @author:     王贤炳
 * @since:      JDK1.8
 * @history:    2021/4/18
 */
class EvalData {
    /** 是否正在评价 */
    private static boolean isEvalution = false;

    public synchronized static void surfaceWaterEvalution(String monitorClassCode) {
        try {
            staticEval(monitorClassCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void staticEval(String monitorClassCode) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("【"+ threadName +"】进入staticEval时间："+ currentTime + "##isEvalution值：" + isEvalution);
        if (isEvalution) {
            return;
        }
        System.out.println("【"+ threadName +"】开始【"+ monitorClassCode +"】数据评价");
        isEvalution = true;
        if ("线程A".equals(threadName)) {
            System.out.println("【" + threadName + "】评价【"+ monitorClassCode +"】数据过程耗时10秒");
            Thread.sleep(10000);
        } else if ("线程B".equals(threadName)) {
            System.out.println("【" + threadName + "】评价【"+ monitorClassCode +"】数据过程耗时12秒");
            Thread.sleep(12000);
        } else if ("线程C".equals(threadName)) {
            System.out.println("【" + threadName + "】评价【"+ monitorClassCode +"】耗时过程15秒");
            Thread.sleep(15000);
        } else if ("线程D".equals(threadName)) {
            System.out.println("【" + threadName + "】评价【"+ monitorClassCode +"】耗时过程3秒");
            Thread.sleep(3000);
        }
        System.out.println("【" + threadName + "】评价【"+ monitorClassCode +"】数据结束！！！");
        isEvalution = false;
    }
}

class DataReport{
    public synchronized void reportDataStorage(String a, String b, String c) throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = simpleDateFormat.format(new Date());
        System.out.println("【" + threadName + "】进入到reportDataStorage方法,插入一条通过记录");
        Thread.sleep(3000);
        /*synchronized (this) {

        }*/
        System.out.println("【" + threadName + "】进入到reportDataStorage方法" + currentTime);
//            Thread.sleep(3000);//不能在同步块里面休眠，否则资源会被释放
        System.out.println("【" + threadName + "】修改评价任务状态");
        System.out.println("【" + threadName + "】触发原始数据评价");
        System.out.println("【" + threadName + "】触发静态评价");
        EvalData.surfaceWaterEvalution(a);
    }

    public void reportBack(String backReason) {
        String threadName = Thread.currentThread().getName();
        System.out.println("【" + threadName + "】" + "退回原因:" + backReason);
    }
}

/**
 * 模拟不同线程不同时间点进入评价的场景案例
 *
 * @author:     王贤炳
 * @since:      JDK1.8
 * @history:    2021/4/18
 */
public class ThreadInterruptDemoCQ {
    public static void main(String[] args) throws InterruptedException {
        Mythred mythred = new Mythred();
        Thread threadA = new Thread(mythred, "线程A");
        Thread threadB = new Thread(mythred, "线程B");
//        Thread threadC = new Thread(mythred, "线程C");
//        Thread threadD = new Thread(mythred, "线程D");
        threadA.start();
        threadB.start();
//        threadC.start();
//        threadD.start();

//        DataReport dataReport = new DataReport();
        Thread threadE = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DataReport dataReport = new DataReport();
                dataReport.reportBack("河流数据不对");
            }
        });

        Thread threadF = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DataReport dataReport = new DataReport();
                dataReport.reportBack("湖库数据不对");
            }
        });
        threadE.start();
        threadF.start();
        /*threadA.join();
        threadB.join();
        threadE.join();
        threadF.join();*/
//        threadC.join();
//        threadD.join();
//        System.out.println("所有子线程都执行完毕！");
    }
}



