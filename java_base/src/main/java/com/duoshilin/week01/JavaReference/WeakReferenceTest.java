package com.duoshilin.week01.JavaReference;

import java.lang.ref.WeakReference;

/**
 * Java 强引用 垃圾回收测试
 * 测试结果：当内存不足时，垃圾回收机制才会回收早期创建的软引用对象
 * Created by duoshilin on 2018/12/15.
 */
public class WeakReferenceTest {

    public static void main(String[] args) {
        WeakReference<BigObject> weakReference = new WeakReference<>(new BigObject("ceshi"));
//        System.gc();
        System.out.println(weakReference.get().name);
    }

}
