package com.duoshilin.java_design_patter.decorator;

/**
 * Created by duoshilin on 2019/2/15.
 */
public abstract class Shape {

    protected String shape;

    public void draw() {
        System.out.println("this is a " + shape);
    }

}
