package com.duoshilin.java_design_patter.factory;

/**
 * Created by duoshilin on 2019/2/11.
 */
public class SMSSender extends Sender{

    @Override
    public void send() {
        System.out.println("This is SMS Sender");
    }
}
