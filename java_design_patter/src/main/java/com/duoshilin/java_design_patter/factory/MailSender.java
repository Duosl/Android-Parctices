package com.duoshilin.java_design_patter.factory;

/**
 * Created by duoshilin on 2019/2/11.
 */
public class MailSender extends Sender{
    @Override
    void send() {
        System.out.println("This is Email Sender");
    }
}
