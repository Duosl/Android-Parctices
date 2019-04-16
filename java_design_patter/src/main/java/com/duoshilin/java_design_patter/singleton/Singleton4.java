package com.duoshilin.java_design_patter.singleton;

/**
 * Created by duoshilin on 2019/2/11.
 *
 * 静态内部类
 *  - 是用静态内部类实现懒加载，在第一次使用时进行初始化
 *
 *  --- 推荐使用 ---
 */
public class Singleton4 {

    private Singleton4(){}

    private static class SingleObject{
        private static Singleton4 INSTANCE = new Singleton4();
    }

    public static Singleton4 getInstance(){
        return SingleObject.INSTANCE;
    }

}
