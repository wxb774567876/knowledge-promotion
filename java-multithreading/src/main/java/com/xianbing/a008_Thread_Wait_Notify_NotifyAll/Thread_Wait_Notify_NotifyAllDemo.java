package com.xianbing.a008_Thread_Wait_Notify_NotifyAll;

class Product {
    private int maxSize;//仓库总容量
    private int size;//当前库存量

    public Product(int maxSize, int size) {
        this.maxSize = maxSize;
        this.size = size;
    }

    public synchronized void produce(int count) {
        String threadName = Thread.currentThread().getName();
        while (count > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("【" + threadName + "】运行，需一次性生产总产品数量：" + count + ";");
            if (size >= maxSize) {
                System.out.println("失败了，【此次想要生产的产品数量】：" + count + "，【库存量】：" + size + "，超出仓库总容量,生产任务无法执行！");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            int produceCount = (size + count) > maxSize ? maxSize - size : count;
            size += produceCount;
            count -= produceCount;
            System.out.println("【此次生产的产品数量】：" + produceCount + "，【生产后仓库库存量】：" + size + "，唤醒其他任务开始执行！！！");
            notifyAll();
        }

    }

    public synchronized void consume(int count) {
        String threadName = Thread.currentThread().getName();
        while (count > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("【" + threadName + "】运行，需一次性消费总产品数量：" + count + ";");
            if (size <= 0) {
                System.out.println("失败了，【此次想要消费的产品数量】：" + count + "，【库存量】：" + size + "，没有可供消费的产品，消费任务暂停！");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int consumeCount = (count - size) > 0 ? size : count;
            size -= consumeCount;
            count -= consumeCount;
            System.out.println("【此次消费的产品数量】：" + consumeCount + "，【消费后的仓库库存量】：" + size + "，此次消费结束，" +
                    "唤醒其他任务开始执行！！！");
            notifyAll();
        }

    }
}

class Producer {
    private Product product;

    public Producer(Product product) {
        this.product = product;
    }

    public void produce(final int count, String threadName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                product.produce(count);
                System.out.println(Thread.currentThread().getName() + "运行结束了！！！");
            }
        }, "生产者" + threadName).start();
    }
}

class Consumer{
    private Product product;

    public Consumer(Product product) {
        this.product = product;
    }

    public void consume(final int count, String threadName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                product.consume(count);
                System.out.println(Thread.currentThread().getName() + "运行结束了！！！");
            }
        }, "消费者" + threadName).start();
    }

}
public class Thread_Wait_Notify_NotifyAllDemo {
    public static void main(String[] args) {
        Product product = new Product(100,0);
        Producer producerThread = new Producer(product);
        Consumer consumerThread = new Consumer(product);

        consumerThread.consume(10, "XFZ_A");

        producerThread.produce(110, "SCZ_A");

        consumerThread.consume(50, "XFZ_B");
        consumerThread.consume(70, "XFZ_C");
        consumerThread.consume(20, "XFZ_D");
        consumerThread.consume(100, "XFZ_E");

        producerThread.produce(60, "SCZ_B");
        producerThread.produce(10, "SCZ_C");
        producerThread.produce(80, "SCZ_D");

        //consumerThread.consume(50, "XFZ_F");
    }
}
