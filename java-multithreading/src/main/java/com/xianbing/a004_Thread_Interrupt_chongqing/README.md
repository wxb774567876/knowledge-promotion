解释join方法
https://blog.csdn.net/u013425438/article/details/80205693

当用户一前一后完成数据审核入库操作时，后一个人提交的数据会无法评价，且相关联的
评价任务状态会被前一个人置为已评价（也有可能是一个人先后操作两类数据入库发生）

第一种情况：
Mythred mythred = new Mythred();
Thread threadA = new Thread(mythred, "线程A");
Thread threadB = new Thread(mythred, "线程B");
threadA.start();
threadA.join();
threadB.start();
threadB.join();

主线程执行到thread.join,进入等待池，等待线程A结束执行继续运行，启动线程B以后，
执行了threadB.join()，主线程继续等待线程B执行完毕

第二种情况：
Mythred mythred = new Mythred();
Thread threadA = new Thread(mythred, "线程A");
Thread threadB = new Thread(mythred, "线程B");
threadA.start();
threadB.start();
threadA.join();
threadB.join();

以上情景，线程A和线程B仍然会交替执行，但是主线程会等待上述两个线程执行完毕执行。
