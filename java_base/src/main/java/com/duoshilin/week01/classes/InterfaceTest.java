package com.duoshilin.week01.classes;

/**
 * Created by duoshilin on 2018/12/10.
 */
public abstract class InterfaceTest {

    void run(){
        System.out.println("run InterfaceTest");
    }

    public InterfaceTest() {
        System.out.println("super.run");
    }

    interface inf{
        default void run() {
            System.out.println();
        }
    }



    public static void main(String[] args) {
        A a=new A();
        B b = new B();
        b.r(a.getRunnable());

    }
}
class A extends InterfaceTest implements InterfaceTest.inf {

    @Override
    public void run() {
//        new Thread(runnable).start();
    }

    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            A.super.run();
        }
    };

    public Runnable getRunnable() {
        return runnable;
    }
}

class B {
    void r(Runnable runnable){
        new Thread(runnable).start();
    }
}