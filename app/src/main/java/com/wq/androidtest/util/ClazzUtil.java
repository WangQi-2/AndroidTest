package com.wq.androidtest.util;

import android.text.TextUtils;

/**
 * Created by qiwang on 2015/8/27.
 */
public final class ClazzUtil {

    /**
     * 取得clazz对象所属类类名 getEncloseingClass是什么鬼
     * @param clazz
     * @return
     */
    public static String getClassName(Class<?> clazz) {
        if (clazz != null) {
            if (!TextUtils.isEmpty(clazz.getSimpleName())) {
                return clazz.getSimpleName();
            }
            return getClassName(clazz.getEnclosingClass());
        }
        return "";
    }
}
