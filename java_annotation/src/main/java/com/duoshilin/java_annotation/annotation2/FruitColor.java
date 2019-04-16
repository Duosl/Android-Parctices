package com.duoshilin.java_annotation.annotation2;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by duoshilin on 2019/2/11.
 */
@Documented
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {

    public enum Color {BLUE, RED, PINK};

    Color value() default Color.PINK;
}
