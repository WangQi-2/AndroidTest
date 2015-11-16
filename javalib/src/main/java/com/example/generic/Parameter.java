package com.example.generic;

import java.util.ArrayList;

/**
 * Created by wangqi on 15/11/16.
 */
public class Parameter {

    public static <T> ArrayList<T> makeList(T... args) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T t : args) {
            arrayList.add(t);
        }

        return arrayList;
    }


    public static void main(String[] args) {

        ArrayList<String> list = makeList("hello", "zhangsan");
        System.out.println(list);
    }
}
