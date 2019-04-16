package com.duoshilin.java_design_patter.observer;

/**
 * Created by duoshilin on 2019/2/13.
 */
public class OctalObserve extends Observe {
    public OctalObserve(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    protected void update() {
        System.out.println(this.getClass().getSimpleName()+": "+Integer.toOctalString(subject.getValue()));
    }
}
