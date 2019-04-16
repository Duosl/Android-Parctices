package com.duoshilin.java_design_patter.observer;

/**
 * Created by duoshilin on  2019/2/13.
 * 观察者
 */
public abstract class Observe {

    protected Subject subject;
    protected abstract void update();
}
