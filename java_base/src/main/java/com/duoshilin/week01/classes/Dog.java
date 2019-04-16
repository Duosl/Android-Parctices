package com.duoshilin.week01.classes;

/**
 * Java 是值传递的
 *
 * Created by duoshilin on 2018/12/10.
 */
public class Dog {
    private String name;

    public Dog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void getAddress(Dog dog){
        super.toString();
    }


}

class MyTest{
    public static void main(String[] args) {
        Dog dog =new Dog("A");
        System.out.println("1:\t"+dog);
        fun1(dog);
        System.out.println("2:\t"+dog);
        dog = fun2(dog);
        System.out.println("3:\t"+dog);


    }

    private static void fun1(Dog dog){
        System.out.println("fun1:\t"+dog);
    }

    private static Dog fun2(Dog dog){
        dog=new Dog("B");
        return dog;
    }

}
