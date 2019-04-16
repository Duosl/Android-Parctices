package com.duoshilin.java_annotation;

import com.duoshilin.java_annotation.annotation2.FruitColor;
import com.duoshilin.java_annotation.annotation2.FruitName;
import com.duoshilin.java_annotation.annotation2.FruitProvider;
import com.duoshilin.java_annotation.annotation2.FruitUtil;

/**
 * Created by duoshilin on 2019/2/11.
 */
public class TestAnnotation2 {


    class Fruit{

        @FruitName("苹果")
        private String fruitName;

        @FruitColor(FruitColor.Color.RED)
        private String fruitColor;

        @FruitProvider(id = 1, name = "红富士", address = "山东省青岛市")
        private String friutProvider;

    }

    @FruitName("橘子")
    @FruitColor
    @FruitProvider(id = 2, name = "家乡味道", address = "北京")
    class Fruit2{

    }

    public static void main(String[] args) {
//        FruitUtil.getFruitInfo(Fruit.class);
        FruitUtil.getFruitInfo(Fruit2.class);
    }
}
