package com.duoshilin.week02.producer_and_consumer.demo2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock 和 Condition 实现的生产者消费者模式
 * Created by duoshilin on 2018/12/18.
 */
public class ProducerConsumerByLock {

    private static int i = 1;
    private static Lock lock = new ReentrantLock();
    private static Condition pCondition = lock.newCondition();
    private static Condition cCondition = lock.newCondition();
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        new Producer("P-1",queue,5).start();
        new Producer("P-2",queue,5).start();
        new Consumer("C-1",queue,5).start();
        new Consumer("C-2",queue,5).start();
        new Consumer("C-3",queue,5).start();
    }

    /**
     * 生产者
     */
    public static class Producer extends Thread{
        Queue<Integer> queue;
        int maxSize;
        String name;


        public Producer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
            this.name = name;
        }

        @Override
        public void run() {
            while (true){
                lock.lock();//加锁
                while (queue.size() == maxSize){
                    try {
                        System.out.println("缓存队列已满，生产者["+name+"]正在歇息，等待消费者消费...");
                        pCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //模拟生产的耗时操作
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                queue.offer(i);
                System.out.println("生产者["+name+"] 生产了第 "+(i++)+" 件商品...");

                //唤醒所有的生产者、消费者
                pCondition.signalAll();
                cCondition.signalAll();

                //休息
                try {
                    pCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lock.unlock();//释放锁
            }
        }
    }

    public static class Consumer extends Thread{
        Queue<Integer> queue;
        int maxSize;
        String name;

        public Consumer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
            this.name = name;
        }

        @Override
        public void run() {
            while (true){
                lock.lock();
                while (queue.isEmpty()){
                    try {
                        System.out.println("缓存队列已空，消费者["+name+"]正在等待新的商品...");
                        cCondition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                //模拟消费的耗时操作
                try {
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int x = queue.poll();
                System.out.println("消费者["+name+"] 使用了第 "+x+" 件商品...");

                pCondition.signalAll();
                cCondition.signalAll();

                //休息
                try {
                    cCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }
    }
}
