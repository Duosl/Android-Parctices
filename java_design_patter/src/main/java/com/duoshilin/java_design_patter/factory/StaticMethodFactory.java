package com.duoshilin.java_design_patter.factory;

/**
 * Created by duoshilin on 2019/2/11.
 *
 * 静态工厂方法模式
 */
public class StaticMethodFactory {

    public static Sender getSMSSender(){
        return new SMSSender();
    }

    public static  Sender getMailSender(){
        return new MailSender();
    }

    public static void main(String[] args) {
        Sender sender = StaticMethodFactory.getMailSender();
        sender.send();
    }
}
