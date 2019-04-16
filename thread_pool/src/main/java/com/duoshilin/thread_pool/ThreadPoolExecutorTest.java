package com.duoshilin.thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {

    ThreadPoolExecutor executor = new ThreadPoolExecutor(3,5,2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(25));

    public static void main(String[] args) {
        new ThreadPoolExecutorTest().executorTest();
        Executors.newFixedThreadPool(10);
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(10);
        Executors.newSingleThreadExecutor();
        Executors.newSingleThreadScheduledExecutor();
        Executors.newWorkStealingPool();
    }

    private void executorTest(){
        executor.allowCoreThreadTimeOut(true);
        for (int i = 1; i <= 30; i++) {
            final int finalI = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+ finalI);
                }
            };
            executor.execute(runnable);
        }

    }

}
