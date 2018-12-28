进程：每个进程都有独立的代码和数据空间（进程上下文），进程间的切换会有较大的开销，一个进程包含1--n个线程。（进程是资源分配的最小单位）

线程：同一类线程共享代码和数据空间，每个线程有独立的运行栈和程序计数器(PC)，线程切换开销小。（线程是cpu调度的最小单位）

线程和进程一样分为五个阶段：创建、就绪、运行、阻塞、终止。

多进程是指操作系统能同时运行多个任务（程序）。

多线程是指在同一程序中有多个顺序流在执行。

谈谈并行、并发或多线程
1.CPU的发展趋势：

     核心数目依旧会越来越多，根据摩尔定律，由于单个核心性能提升有着严重的瓶颈问题，普通的PC桌面在2018年可能会到24核心。

2.并发和并行的区别：

 所有的并发处理都有排队等候，唤醒和执行这三个步骤，所以并发是宏观的观念，在微观上他们都是序列被处理的，只不过资源不会在某一个上被阻塞（一般是通过时间片轮转），所以在宏观上多个几乎同时到达的请求同时在被处理。如果是同一时刻到达的请求也会根据优先级的不同，先后进入队列排队等候执行。

 并发与并行是两个既相似但是却不相同的概念：

     并发性：又称共行性，是指处理多个同时性活动的能力，。

     并行：指同时发生两个并发事件，具有并发的含义。并发不一定并行，也可以说并发事件之间不一定要同一时刻发生。 

 并发的实质是一个物理CPU（也可以是多个物理CPU）在若干个程序之间多路复用，并发性是对有限物理资源强制行使 多用户共享以提高效率。

 并行指两个或两个以上事件或活动在同一时刻发生，在多道程序环境下，并行使多个程序同一时刻可在不同CPU上同时执行。    

 并发是在同一个cpu上同时（不是真正的同时，而是看来是同时，因为CPU要在多个程序之间切换）运行多个程序。

 并行是每一个CPU运行一个程序。

 打个比方：并发就像一个人（CPU）喂两个小孩（程序）吃饭，表面上是两个小孩在吃饭，实际是一个人在喂。并行就是两个人喂两个小孩子吃饭。 



博文推荐：
https://www.cnblogs.com/yjd_hycf_space/p/7526608.html

https://blog.csdn.net/chenruijia170707/article/details/78505351
