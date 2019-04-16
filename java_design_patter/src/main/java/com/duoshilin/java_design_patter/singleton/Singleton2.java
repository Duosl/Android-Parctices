package com.duoshilin.java_design_patter.singleton;

/**
 * Created by duoshilin on 2019/2/11.
 * 懒汉式1
 *  - 第一次用到时进行初始化， 不存在饿汉式的缺点
 *  - 是线程不安全的
 *
 *  缺点：
 *      每次获取实例时都需要加锁，造成浪费
 *
 *   --- 不推荐使用 ---
 */
public class Singleton2 {

    private static Singleton2 instance = null;

    private Singleton2(){}

    public static Singleton2 getInstance(){
        synchronized (Singleton2.class){
            if (instance == null){
                instance = new Singleton2();
            }
            return instance;
        }

    }
}
