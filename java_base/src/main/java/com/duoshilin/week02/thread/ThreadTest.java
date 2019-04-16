package com.duoshilin.week02.thread;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * 要求：（可在Runnable中修改代码，但主逻辑不变）
 * 1. 看代码说出runA中i可能出现的值，如何验证？
 * 2. 运行runA 和 RunB 观察运行结果
 * 3. 如何保证runA.i===9 且 runB.i===25(===表示恒等于)
 * 4. 如何在主线程中保证tt.i==15后,并输出打印正确的值后，退出程序（提示：可以用System.exit(0)退出整个程序）
 * 建议在处理第四个问题是优先理解wait()、notify()、join()、yeild()四个方法
 *
 * Created by duoshilin on 2018/12/17.
 */
public class ThreadTest {
    int i=0;
    int a=3;
    int b=5;
    volatile boolean isNeedExist=false;

    Runnable runA=new Runnable() {
        @Override
        public void run() {
            while (!isNeedExist) {
                i = a;
                synchronized (this){
                    i = i * a;
                    Thread.yield();
                    System.out.println(Thread.currentThread().getName()+"--a:" + i);
                    if (i==15){
                        break;
                    }
                }
            }
        }
    };

    Runnable runB = new Runnable() {
        @Override
        public void run() {
            while (!isNeedExist) {
                i = b;
                synchronized (this){
                    i = i * b;
                    System.out.println(Thread.currentThread().getName()+"--b:" + i);
                    if (i == 15){
                        break;
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        ThreadTest t=new ThreadTest();
//        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,10,TimeUnit.MINUTES,new SynchronousQueue<>());
//        executor.execute(t.runA);
//        executor.execute(t.runB);

        Thread threadA = new Thread(t.runA);
        Thread threadB = new Thread(t.runB);

        threadA.start();
        threadB.start();

//        threadA.join();
//        threadB.join();

        while (!t.isNeedExist){
            if (t.i == 15){
                t.isNeedExist = true;
                System.out.println(Thread.currentThread().getName()+"--main: " + t.i);
            }

        }

//        System.exit(0);

    }
}
