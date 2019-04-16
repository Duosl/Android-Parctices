package com.duoshilin.week01.JavaReference;

/**
 * Java 强引用 垃圾回收测试
 * 测试结果：垃圾收集器永远不会回收掉强引用的对象
 * Created by duoshilin on 2018/12/15.
 */
public class StrongReferenceTest {

    public static void main(String[] args) {
        int count = 500; //创建很多对象，确保造成OOM
        BigObject[] bgs = new BigObject[count];
        for (int i = 0; i < count; i++) {
            bgs[i] = new BigObject("Object-" + i);
        }
        for (int i = 0; i < count; i++) {
            System.out.println(bgs[i].name);
        }
    }

}
