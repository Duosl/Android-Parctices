package com.duoshilin.java_design_patter.singleton;

/**
 * 饿汉式
 *  - 类加载是进行初始化 是线程安全的
 *  - 写法简单
 *
 *  缺点：
 *      类加载时进行初始化，没有达到懒加载的效果，如果该自始至终没有使用过这个实例，那么就会造成内存浪费
 *
 *   --- 可用 ---
 */
public class Singleton {

    private static final Singleton INSTANCE = new Singleton();

    private Singleton(){}

    public static Singleton getInstance(){
        return INSTANCE;
    }
}
