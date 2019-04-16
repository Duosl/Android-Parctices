package com.duoshilin.java_design_patter.singleton;

/**
 * Created by duoshilin on 2019/2/11.
 * 枚举实现
 *  - 内存中只存在一个对象
 *
 *  缺点：
 *      创建对象时，不是使用new，而是需要记住对应的方法，团队合作时，可能给其他开发人员造成困扰，特别是在看不到源码的情况下
 *
 */
public enum  Singleton5 {

    INSTANCE;

    Singleton5() {
    }
}
