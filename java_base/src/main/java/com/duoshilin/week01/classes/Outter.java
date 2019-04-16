package com.duoshilin.week01.classes;

/**
 * Java 内部类
 *
 * Created by duoshilin on 2018/12/7.
 */
public class Outter {

    private String address = "北京";

    public class Inner1{

        public String getAdress(){
            return address;
        }
    }

    public static class Inner2 {
        public String getAdress(){
            return "";
        }
    }

    public static void main(String[] args) {
//        Outter parent = new Outter();
//        Inner1 son1=parent.new Inner1();
        Inner1 son1=new Outter().new Inner1();
        System.out.println(son1.getAdress());

        Inner2 son2=new Outter.Inner2();
    }

}
