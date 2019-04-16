package com.duoshilin.java_design_patter.factory;

/**
 * Created by duoshilin on 2019/2/11.
 *
 * 简单工厂模式
 */
public class SimpleFactory {

    public Sender getSender(String senderType){
        switch (senderType){
            case "sms":
                return new SMSSender();
            case "mail":
                return new MailSender();
            default:
                return null;
        }
    }



    public static void main(String[] args) {
        Sender sender = new SimpleFactory().getSender("sms");
        sender.send();
    }


}

