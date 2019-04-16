package com.duoshilin.java_annotation.annotation2;

import java.lang.reflect.Field;

/**
 * Created by duoshilin on 2019/2/11.
 */
public class FruitUtil {

    public static void getFruitInfo(Class<?> clz){

        if (clz.isAnnotationPresent(FruitName.class) || clz.isAnnotationPresent(FruitColor.class) || clz.isAnnotationPresent(FruitProvider.class)){
            getClassAnnotationInfo(clz);
        }else {
            getFieldAnnotationInfo(clz.getDeclaredFields());
        }

    }

    private static void getFieldAnnotationInfo(Field[] fields){
        StringBuffer sb = new StringBuffer();
        for (Field field : fields){
            if (field.isAnnotationPresent(FruitName.class)){
                sb.append("名称："+field.getAnnotation(FruitName.class).value()+"\n");
            }
            if (field.isAnnotationPresent(FruitColor.class)){
                sb.append("颜色："+field.getAnnotation(FruitColor.class).value()+"\n");
            }
            if (field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider provider = field.getAnnotation(FruitProvider.class);
                sb.append("供应商编号："+provider.id()+"\n");
                sb.append("供应商名称："+provider.name()+"\n");
                sb.append("供应商地址："+provider.address()+"\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void getClassAnnotationInfo(Class<?> clz){
        StringBuffer sb = new StringBuffer();
        if (clz.isAnnotationPresent(FruitName.class)){
            sb.append("名称："+clz.getAnnotation(FruitName.class).value()+"\n");
        }
        if (clz.isAnnotationPresent(FruitColor.class)){
            sb.append("颜色："+clz.getAnnotation(FruitColor.class).value()+"\n");
        }
        if (clz.isAnnotationPresent(FruitProvider.class)){
            FruitProvider provider = clz.getAnnotation(FruitProvider.class);
            sb.append("供应商编号："+provider.id()+"\n");
            sb.append("供应商名称："+provider.name()+"\n");
            sb.append("供应商地址："+provider.address()+"\n");
        }
        System.out.println(sb.toString());
    }
}
