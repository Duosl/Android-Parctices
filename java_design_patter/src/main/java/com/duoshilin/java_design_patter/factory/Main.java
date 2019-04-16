package com.duoshilin.java_design_patter.factory;

/**
 * Created by duoshilin on 2019/2/11.
 *
 * 抽象工厂模式
 * 抽象工厂是围绕一个超级工厂创建其他工厂。该超级工厂又称为其他工厂的工厂
 * 参考：http://www.runoob.com/design-pattern/abstract-factory-pattern.html
 */
public class Main {

    public static void main(String[] args) {
        Provider provider = new SMSSenderProvider();
        Sender sender = provider.produce();
        sender.send();
    }
}
