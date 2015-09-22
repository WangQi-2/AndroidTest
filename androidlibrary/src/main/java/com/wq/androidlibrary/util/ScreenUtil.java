package com.wq.androidlibrary.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by wangqi on 15/9/13.
 */
public class ScreenUtil {


    public static float dp2px(Context context, float dp) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static float px2dp(Context context, float px) {
        float dp = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
        return dp;
    }

    public static float getScreenWidthInInch(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        return width / metrics.xdpi;
    }
    public static float getScreenHeigthInInch(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int height = metrics.heightPixels;
        return height / metrics.ydpi;
    }

    public static float getScreenSizeInInch(Context context){
        float width = getScreenWidthInInch(context);
        float height = getScreenHeigthInInch(context);
        double size = Math.sqrt(width * width + height * height);
        return (float) size;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }


}
