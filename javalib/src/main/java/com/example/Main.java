package com.example;

import com.example.thread.MutilThreadDownloader;

/**
 * Created by wangqi on 15/10/21.
 */
public class Main {

    public static void main(String[] args) {

//        new TestThreadPool().run();
        new MutilThreadDownloader().run();
    }

}
