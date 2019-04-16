package com.duoshilin.week01.Generic;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Java  泛型测试
 *
 * Created by duoshilin on 2018/12/10.
 */
public class GenericTest {

    //这个类是个泛型类
    public class Generic<T> {
        private T key;

        public Generic(T key) {
            this.key = key;
        }

        //我想说的其实是这个，虽然在方法中使用了泛型，但是这并不是一个泛型方法。
        //这只是类中一个普通的成员方法，只不过他的返回值是在声明泛型类已经声明过的泛型。
        //所以在这个方法中才可以继续使用 T 这个泛型。
        public T getKey() {
            return key;
        }

        /**
         * 这个方法显然是有问题的，在编译器会给我们提示这样的错误信息"cannot reslove symbol E"
         * 因为在类的声明中并未声明泛型E，所以在使用E做形参和返回值类型时，编译器会无法识别。
         public E setKey(E key){
         this.key = keu
         }
         */
    }
    /* --------------------------------泛型类定义结束-------------------------------------- */

    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * 泛型的数量也可以为任意多个
     * 如：public <T,K> K showKeyName(Generic<T> container){
     * ...
     * }
     */
    public <T> T showKeyName(Generic<T> container) {
        System.out.println("container key :" + container.getKey());
        //当然这个例子举的不太合适，只是为了说明泛型方法的特性。
        T test = container.getKey();
        return test;
    }

    //这也不是一个泛型方法，这就是一个普通的方法，只是使用了Generic<Number>这个泛型类做形参而已。
    public void showKeyValue1(Generic<Number> obj) {
        Log.d("泛型测试", "key value is " + obj.getKey());
    }

    //这也不是一个泛型方法，这也是一个普通的方法，只不过使用了泛型通配符?
    //同时这也印证了泛型通配符章节所描述的，?是一种类型实参，可以看做为Number等所有类的父类
    public void showKeyValue2(Generic<?> obj) {
        Log.d("泛型测试", "key value is " + obj.getKey());
    }

    /**
     * 这个方法是有问题的，编译器会为我们提示错误信息："UnKnown class 'E' "
     * 虽然我们声明了<T>,也表明了这是一个可以处理泛型的类型的泛型方法。
     * 但是只声明了泛型类型T，并未声明泛型类型E，因此编译器并不知道该如何处理E这个类型。
     public <T> T showKeyName(Generic<E> container){
     ...
     }
     */

    /**
     * 这个方法也是有问题的，编译器会为我们提示错误信息："UnKnown class 'T' "
     * 对于编译器来说T这个类型并未项目中声明过，因此编译也不知道该如何编译这个类。
     * 所以这也不是一个正确的泛型方法声明。
     * public void showkey(T genericObj){
     * <p>
     * }
     */

    static class CollectionGenFoo<T extends Collection> {
        private T x;

        public CollectionGenFoo(T x) {
            this.x = x;
        }

        public T getX() {
            return x;
        }

        public void setX(T x) {
            this.x = x;
        }
    }

    static class ArrayAlg {
        public static <T> T getMiddle(T... a) {
            return a[a.length / 2];
        }

        public static <T extends Comparable> T min(T[] a) // almost correct
        {
            if (a == null || a.length == 0) {
                return null;
            }
            T smallest = a[0];
            for (int i = 1; i < a.length; i++) {
                if (smallest.compareTo(a[i]) > 0) {
                    smallest = a[i];
                }
            }
            return smallest;
        }
    }

    public static void main(String[] args) {
        System.out.println(ArrayAlg.min(new Integer[]{3, 1729, 0}));
    }
}
