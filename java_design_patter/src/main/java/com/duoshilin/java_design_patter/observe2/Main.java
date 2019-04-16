package com.duoshilin.java_design_patter.observe2;

/**
 * Created by duoshilin on 2019/2/13.
 */
public class Main {
    public static void main(String[] args) {
        User zhangsan = new User("张三");
        User lisi = new User("李四");
        User zhoujielun = new User("周杰伦");
        User dilireba = new User("迪丽热巴");

        zhangsan.follow(zhoujielun);
        zhangsan.follow(dilireba);
        lisi.follow(dilireba);
        zhoujielun.follow(dilireba);
        dilireba.follow(zhoujielun);

        dilireba.addArticleNum();
        zhoujielun.addArticleNum();
        zhoujielun.addArticleNum();
        dilireba.addArticleNum();

        System.out.println(zhangsan);
        System.out.println(lisi);
        System.out.println(zhoujielun);
        System.out.println(dilireba);
    }
}
