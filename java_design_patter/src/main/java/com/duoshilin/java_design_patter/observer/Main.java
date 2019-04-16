package com.duoshilin.java_design_patter.observer;

/**
 * Created by duoshilin on 2019/2/13.
 * 观察者模式 是典型的一对多，当一个对象Subject被修改时，则会自动通知它的
 */
public class Main {

    public static void main(String[] args) {
        Subject subject = new Subject();

        BinaryObserve binaryObserve = new BinaryObserve(subject);
        OctalObserve octalObserve = new OctalObserve(subject);
        HexObserve hexObserve = new HexObserve(subject);

        subject.setValue(10);
        subject.setValue(18);

//        subject.disattach(hexObserve);
        subject.setValue(15);
        subject.setValue(25);


    }
}
