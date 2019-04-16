package com.duoshilin.java_design_patter.decorator;

/**
 * Created by duoshilin on 2019/2/15.
 */
public class Main {

    public static void main(String[] args) {
        Shape circle = new Circle();
        circle.draw();

        RedDecorator decorator = new RedDecorator();
        decorator.decorate(circle);

        circle.draw();
    }
}
