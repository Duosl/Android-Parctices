package com.duoshilin.week01.JavaReference;

import java.lang.ref.SoftReference;

/**
 * Java 软引用 垃圾回收测试
 * 测试结果：当内存不足时，垃圾回收机制才会回收早期创建的软引用对象
 * Created by duoshilin on 2018/12/15.
 */
public class SoftReferenceTest {

    public static void main(String[] args) {
        int count = 500; //创建很多对象，确保造成OOM
        SoftReference[] softReferences = new SoftReference[count];
        for (int i = 0; i < count; i++) {
            softReferences[i] = new SoftReference(new BigObject("Object-" + i));
        }
        System.out.println(((BigObject)softReferences[softReferences.length-1].get()).name);
        for (int i = 0; i < 10; i++) {
            System.out.println(((BigObject)softReferences[i].get()).name);
        }
    }

}
