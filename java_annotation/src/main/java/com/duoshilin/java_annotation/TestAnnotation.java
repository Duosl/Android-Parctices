package com.duoshilin.java_annotation;

import com.duoshilin.java_annotation.annotation.CheckBugs;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestAnnotation {


    class Test{

        int a = 1;
        int b = 2;

        @CheckBugs
        public void add(){
            System.out.println("1 + 2 = "+(1+2));
        }

        @CheckBugs
        public void divide(){
            System.out.println("1 / 0 = "+(1/0));
        }

    }

    public static void main(String[] args) {
//        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Test testObj = new TestAnnotation().new Test();
        Class<?> clz = testObj.getClass();

        Method[] methods = clz.getDeclaredMethods();
        for (Method method : methods) {
            try {
                Annotation annotation = method.getAnnotation(CheckBugs.class);
                if (method.isAnnotationPresent(CheckBugs.class)) {
                    method.setAccessible(true);
                    method.invoke(testObj, null);
                }
            } catch (Exception e) {
//                e.printStackTrace();
                System.out.println("method【" + clz.getName() + "." + method.getName() + "()】 has error: " + e.getCause().getClass().getSimpleName() + " : " + e.getCause().getMessage());
            }
        }


    }
}
