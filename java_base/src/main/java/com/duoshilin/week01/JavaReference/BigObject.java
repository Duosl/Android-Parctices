package com.duoshilin.week01.JavaReference;

/**
 * 我是一个大的对象
 * Created by duoshilin on 2018/12/15.
 */
public class BigObject {
    String name;
    long[] values;

    public BigObject(String name) {
        this.name = name;
        this.values = new long[1024000];
    }
}
