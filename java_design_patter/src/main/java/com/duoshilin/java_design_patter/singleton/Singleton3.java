package com.duoshilin.java_design_patter.singleton;

/**
 * Created by duoshilin on 2019/2/11.
 *
 * 懒汉式(双重校验锁)
 *   - 只有在第一次创建时，需要进行加锁，之后可以直接进行判断，返回实例
 *
 *   --- 推荐使用 ---
 */
public class Singleton3 {

    private static Singleton3 INSTANCE = null;

    private Singleton3(){}

    public static Singleton3 getInstance(){
        if (INSTANCE == null){
            synchronized (Singleton3.class){
                if (INSTANCE == null){
                    INSTANCE = new Singleton3();
                }
            }
        }
        return INSTANCE;
    }
}
