package com.duoshilin.java_annotation.annotation2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by duoshilin on 2019/2/11.
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@Inherited
public @interface FruitProvider {

    //供应商id
    public int id() default -1;

    //供应商名称
    public String name() default "未知";

    //供应商地址
    public String address() default "未知";
}
