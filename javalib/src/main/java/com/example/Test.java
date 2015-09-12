package com.example;

import java.util.Objects;

public class Test {

    public static void main(String[] args) {
        System.out.println("test");
        Animal animal = new Animal(1, 1.3f, "waa");
        System.out.println(animal);
        release(animal);
        System.out.println(animal);
    }

    public static void release(Object o) {
        o = null;
    }

}
