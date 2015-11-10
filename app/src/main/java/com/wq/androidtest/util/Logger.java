package com.wq.androidtest.util;

import android.util.Log;

import com.wq.androidlibrary.util.ClazzUtil;

import java.util.Calendar;

/**
 * Created by qiwang on 2015/8/27.
 * 日志工具类
 * 仅支持三种类型日志 info，debug，error
 * 参照了andbase以及Phoenix
 */
public final class Logger {

    //设置一个默认的TAG
    public static final String TAG = "x504";
    //起始执行时间
    public static long startLogTimeInMillis = 0;
    //标记日志是否允许（打开）
    private static boolean logEnabled_i = true;
    private static boolean logEnabled_d = true;
    private static boolean logEnabled_e = true;

    /**
     * 描述：记录当前时间毫秒.
     */
    public static void prepareLog(String tag) {
        Calendar current = Calendar.getInstance();
        startLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, "日志计时开始：" + startLogTimeInMillis);
    }

    /**
     * 描述：打印这次的执行时间毫秒，需要首先调用prepareLog().
     *
     * @param tag       标记
     * @param message   描述
     * @param printTime 是否打印时间
     */
    public static void d(String tag, String message, boolean printTime) {
        Calendar current = Calendar.getInstance();
        long endLogTimeInMillis = current.getTimeInMillis();
        Log.d(tag, message + ":" + (endLogTimeInMillis - startLogTimeInMillis) + "ms");
    }

    public static void i(String tag, String msg) {
        if (logEnabled_i) Log.i(tag, msg);
    }

    public static void d(String tag, String msg) {
        if (logEnabled_d) Log.d(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (logEnabled_e) Log.e(tag, msg);
    }

    /**
     * 这三个log是干嘛的
     *
     * @param msg
     */
    public static void i(String msg) {
        i(TAG, msg);
    }

    public static void d(String msg) {
        d(TAG, msg);
    }

    public static void e(String msg) {
        e(TAG, msg);
    }

    /**
     * 开关控制
     */
    public static void openAll() {
        logEnabled_i = true;
        logEnabled_d = true;
        logEnabled_e = true;
    }

    public static void closeAll() {
        logEnabled_i = false;
        logEnabled_d = false;
        logEnabled_e = false;
    }

    public static void setDebugLevel(boolean info, boolean debug, boolean error) {
        logEnabled_i = info;
        logEnabled_d = debug;
        logEnabled_e = error;
    }

    private static String getLocation() {
        final String className = Logger.class.getName();
        final StackTraceElement[] traces = Thread.currentThread().getStackTrace();
        boolean found = false;

        for (StackTraceElement trace : traces) {
            try {
                if (found) {
                    if (!trace.getClassName().startsWith(className)) {
                        Class<?> clazz = Class.forName(trace.getClassName());
                        return "[" + ClazzUtil.getClassName(clazz) + ":" + trace.getMethodName() + ":" + trace.getLineNumber() + "]: ";
                    }
                } else if (trace.getClassName().startsWith(className)) {
                    found = true;
                }
            } catch (ClassNotFoundException ignored) {
            }
        }
        return "[]: ";
    }

}
