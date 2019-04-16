package com.duoshilin.java_design_patter.observer;

/**
 * Created by duoshilin on 2019/2/13.
 */
public class BinaryObserve extends Observe {
    public BinaryObserve(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    protected void update() {
        System.out.println(this.getClass().getSimpleName()+": "+Integer.toBinaryString(subject.getValue()));
    }
}
