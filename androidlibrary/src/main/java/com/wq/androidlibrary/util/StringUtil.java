package com.wq.androidlibrary.util;

import java.text.DecimalFormat;

/**
 * Created by wangqi on 15/9/9.
 */
public class StringUtil {

    public static String FormatDecimal(double d){
        DecimalFormat format = new DecimalFormat();
        return  format.format(d);
    }

}
