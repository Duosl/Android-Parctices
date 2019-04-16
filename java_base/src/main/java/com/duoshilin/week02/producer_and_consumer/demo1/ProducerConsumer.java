package com.duoshilin.week02.producer_and_consumer.demo1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 通过wait()和notify() 实现的生产者和消费者模型
 * Created by duoshilin on 2018/12/18.
 */
public class ProducerConsumer {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        new Producer("P-1",queue,5).start();
        new Producer("P-2",queue,5).start();
        new Consumer("C-1",queue,5).start();
        new Consumer("C-2",queue,5).start();
        new Consumer("C-3",queue,5).start();
    }

    public static class Producer extends Thread{

        private Queue<Integer> queue;//缓存队列
        String name;    //生产出的产品名
        int maxSize;    //最大生产量
        volatile static int i=1;


        public Producer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.queue = queue;
            this.name = name;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.size() == maxSize){
                        try {
                            System.out.println("缓存队列已满，生产者["+name+"]正在歇息，等待消费者消费...");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //System.out.println("生产者["+name+"] 正在生产第 "+i+" 件商品...");
                    //模拟生产的耗时操作
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    queue.offer(i);
                    System.out.println("生产者["+name+"] 生产了第 "+(i++)+" 件商品...");
                    queue.notifyAll();
                    //使当前线程释放锁，让其他线程有机会执行，模拟出生产和消费可同时进行的效果
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static class Consumer extends Thread{

        private Queue<Integer> queue;//缓存队列
        String name;    //当前消费的产品名
        int maxSize;

        public Consumer(String name, Queue<Integer> queue, int maxSize) {
            super(name);
            this.queue = queue;
            this.name = name;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true){
                synchronized (queue){
                    while (queue.isEmpty()){
                        try {
                            System.out.println("缓存队列已空，消费者["+name+"]正在等待新的商品...");
                            queue.wait();
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
                    queue.notifyAll();
                    //使当前线程释放锁，让其他线程有机会执行，模拟出生产和消费可同时进行的效果
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
