package com.example.generic;

import java.util.HashMap;
import java.util.List;

/**
 * Created by wangqi on 15/11/16.
 */
public class Dog implements Generator<Dog> {


    @Override
    public Dog next() {

        HashMap<Object, List<? extends Dog>> map = new HashMap<>();

        return this;
    }
}
