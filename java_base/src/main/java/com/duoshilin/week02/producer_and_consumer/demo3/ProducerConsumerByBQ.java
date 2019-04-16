package com.duoshilin.week02.producer_and_consumer.demo3;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 生产者消费者模式：使用{@link java.util.concurrent.BlockingQueue}实现
 * Created by duoshilin on 2018/12/18.
 */
public class ProducerConsumerByBQ {

    private static volatile int i = 1;
    private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>(5);

    public static void main(String[] args) {
        Thread p1 = new Producer("P-1", queue, 5);
        Thread p2 = new Producer("P-2", queue, 5);
        p1.start();
        p2.start();
        new Consumer("C-1", queue, 5).start();
        new Consumer("C-2", queue, 5).start();
        new Consumer("C-3", queue, 5).start();


        try {
            p1.join();
            p2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    /**
     * 生产者
     */
    public static class Producer extends Thread {
        LinkedBlockingQueue<Integer> queue;
        int maxSize;
        String name;


        public Producer(String name, LinkedBlockingQueue<Integer> queue, int maxSize) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (Producer.class){
                        queue.put(i);
                        System.out.println("生产者[" + name + "] 生产了第 " + (i++) + " 件商品...");
                    }
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer extends Thread {
        LinkedBlockingQueue<Integer> queue;
        int maxSize;
        String name;

        public Consumer(String name, LinkedBlockingQueue<Integer> queue, int maxSize) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (Consumer.class){
                        int x = queue.take();
                        System.out.println("消费者[" + name + "] 使用了第 " + x + " 件商品...");
                    }
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
