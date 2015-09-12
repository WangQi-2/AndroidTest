package com.wq.androidtest.util;

import android.os.Environment;

/**
 * Created by wangqi on 15/9/9.
 */
public class FileUtil {
    public static String getExternalStoragePath() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return Environment.getExternalStorageDirectory().toString();
        }

        return null;
    }
}
