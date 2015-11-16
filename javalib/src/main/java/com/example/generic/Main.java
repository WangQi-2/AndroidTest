package com.example.generic;

/**
 * Created by wangqi on 15/11/16.
 */
public class Main {

    public static void main(String[] args) {
        LinkedStack<String> stack = new LinkedStack<>();
        String a = "a";
        String b = "b";
        String c = "c";
        String d = "d";
        String e = "e";

        stack.push(a);
        stack.push(b);
        stack.push(c);
        stack.push(d);
        stack.push(e);

        while (!stack.end()) {
            System.out.println(stack.pop());
        }
    }

}
