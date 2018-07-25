package com.xianbing.a008_Thread_Wait_Notify_NotifyAll;

class Product {
    private int maxSize;//仓库总容量
    private int size;//当前库存量

    public Product(int maxSize, int size) {
        this.maxSize = maxSize;
        this.size = size;
    }

    public synchronized void produce(int count) {
        while (count > 0) {
            String threadName = Thread.currentThread().getName();
            System.out.print("【" + threadName + "】运行，想生产的总产品数量：" + count + ";");
            if (size >= maxSize) {
                System.out.println("【此次生产的产品数量】：" + count + "，【库存量】：" + size + "，超出仓库总容量,生产任务无法执行！");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int produceCount = (size + count) > maxSize ? maxSize - size : count;
            size += produceCount;
            count -= produceCount;
            System.out.println("【此次生产的产品数量】：" + produceCount + "，【当前仓库库存量】：" + size + "，唤醒其他任务开始执行！！！");
            notifyAll();
        }

    }

    public synchronized void consume(int count) {
        String threadName = Thread.currentThread().getName();
        System.out.print("【" + threadName + "】运行，想消费的总产品数量：" + count + ";");
        while (count > 0) {
            if (size <= 0) {
                System.out.println("【此次消费的产品数量】：" + count + "，【库存量】：" + size + "，没有可供消费的产品，消费任务暂停！");
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int consumeCount = (count - size) > 0 ? size : count;
            size -= consumeCount;
            count -= consumeCount;
            System.out.println("【此次消费的产品数量】：" + count + "，【当前仓库库存量】：" + size + "，此次消费结束，" +
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

    public void produce(final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                product.produce(count);
            }
        }, "生产者").start();
    }
}

class Consumer{
    private Product product;

    public Consumer(Product product) {
        this.product = product;
    }

    public void consume(final int count) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                product.consume(count);
            }
        }, "消费者").start();
    }

}
public class Thread_Wait_Notify_NotifyAllDemo {
    public static void main(String[] args) {
        Product product = new Product(100,0);
        Producer producerThread = new Producer(product);
        Consumer consumerThread = new Consumer(product);

        consumerThread.consume(10);

        producerThread.produce(110);

        consumerThread.consume(50);
        consumerThread.consume(70);
        consumerThread.consume(20);
        consumerThread.consume(100);

        producerThread.produce(60);
        producerThread.produce(10);
        producerThread.produce(80);
    }
}
