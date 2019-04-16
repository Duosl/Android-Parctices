package com.duoshilin.week01.Generic;

import android.annotation.SuppressLint;

import java.time.LocalDate;

/**
 * Java 泛型
 *
 * Created by duoshilin on 2018/12/10.
 */
public class GenericTest2 {

    private static <T extends Comparable> Pair<T> minmax(T[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        T min = arr[0];
        T max = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].compareTo(min) < 0) min = arr[i];
            if (arr[i].compareTo(max) > 0) max = arr[i];
        }
        return new Pair<>(min, max);
    }


    public static void main(String[] args) {
        @SuppressLint({"NewApi", "LocalSuppress"})
        LocalDate[] birthday = {
                LocalDate.of(1996, 10, 2),
                LocalDate.of(1997, 6, 2),
                LocalDate.of(1995, 8, 5),
                LocalDate.of(2001, 11, 2),
        };

        minmax(birthday);

    }
}

class Pair<T> {
    T min = null;
    T max = null;

    public Pair(T min, T max) {
        System.out.println("min: " + min + ", max: " + max);
    }


}
