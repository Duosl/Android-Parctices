package com.duoshilin.week02.thread;

/**
 * ThreadLocal 的使用
 * Created by duoshilin on 2018/12/17.
 */
public class ThreadLocalTest {
    static ThreadLocal<String> values = new ThreadLocal<String>();
//    static ThreadLocal<String> values = new ThreadLocal<String>(){
//        @Override
//        protected String initialValue() {
//            return Thread.currentThread().getName();
//        }
//    };


    public String get(){
        return values.get();
    }

    public void set(){
        values.set(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        values.set("0");
        System.out.println(values.get());

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    values.set(""+Thread.currentThread().getId());
                    System.out.println(values.get());
                }
            }).start();
        }

    }

}
