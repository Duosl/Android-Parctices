package com.duoshilin.java_design_patter.factory;

/**
 * Created by duoshilin on 2019/2/11.
 */
public class MailSenderProvider implements Provider {
    @Override
    public Sender produce() {
        return new MailSender();
    }
}
