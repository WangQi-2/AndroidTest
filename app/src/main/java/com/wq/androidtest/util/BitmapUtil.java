package com.wq.androidtest.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wangqi on 15/9/9.
 */
public class BitmapUtil {

    public static Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        //TODO java.net network frame
        URLConnection con = null;
        InputStream is = null;
        try {
            URL imageURL = new URL(url);
            con = imageURL.openConnection();
            con.setDoInput(true);
            con.connect();
            is = con.getInputStream();
            //TODO BitmapFactory
//            bitmap = BitmapFactory.decodeStream(is);
            bitmap = BitmapFactory.decodeStream(is, null, null);
        } catch (Exception e) {
            //TODO lib log
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                //TODO lib log
            }
        }
        return bitmap;
    }

    public static Bitmap getBitmap(File file) {
        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeFile(file.getPath());
            //TODO JAVA FILE SYSTEM
//            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (Exception e) {
            //TODO lib log
        }
        return bitmap;
    }

    public static Bitmap getBitmap(String url, int desireWidth, int desiredHeight) {
        Bitmap bitmap = getBitmap(url);
        Bitmap resizedBitmap = getCutBitmap(bitmap, desireWidth, desiredHeight);
        if (bitmap != resizedBitmap) {
            bitmap.recycle();
            bitmap = null;
        }
        return resizedBitmap;
    }

    public static Bitmap getBitmap(InputStream is, int desireWidth, int desiredHeight) {
        Bitmap src = BitmapFactory.decodeStream(is);
        Bitmap dst = getCutBitmap(src, desireWidth, desiredHeight);
        return dst;
    }

    public static Bitmap getBitmap(byte[] data, int desireWidth, int desiredHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        float scale = getScale(srcWidth, srcHeight, desireWidth, desiredHeight);
        //TODO 这里需要判断scale是否为0??? andbase did it
        int dstWidth = (int) (srcWidth * scale);
        int dstHeight = (int) (srcHeight * scale);

        options.inPreferredConfig = Bitmap.Config.RGB_565;

        return null;
    }


    public static Bitmap getCutBitmap(Bitmap src, int desiredWidth, int desireHeight) {

        int width = src.getWidth();
        int height = src.getHeight();
        int offsetX = 0;
        int offsetY = 0;

        if (width > desiredWidth) {
            offsetX = (width - desiredWidth) / 2;
        } else {
            desiredWidth = width;
        }

        if (height > desireHeight) {
            offsetY = (height - desireHeight) / 2;
        } else {
            desireHeight = height;
        }
        //TODO maybe return the same one??
        Bitmap dst = Bitmap.createBitmap(src, offsetX, offsetY, desiredWidth, desireHeight);
        return dst;
    }

    public static Bitmap getScaleBitmap(Bitmap src, int desiredWidth, int desireHeight) {
        Bitmap bitmap = null;
        return bitmap;
    }

    public static float getScale(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
        float scaleWidth = (float) desiredWidth / srcWidth;
        float scaleHeight = (float) desiredHeight / srcHeight;
        return Math.max(scaleWidth, scaleHeight);
    }

    public static int getSimpleSize(int srcWidth, int srcHeight, int desiredWidth, int desiredHeight) {
        double wr = (double) srcWidth / desiredWidth;
        double hr = (double) srcHeight / desiredHeight;
        double ratio = Math.min(wr, hr);
        //TODO SAMPLESIZE
        int sampleSize = 1;
        while ((sampleSize * 2) <= ratio) {
            sampleSize *= 2;
        }
        return sampleSize;
    }
}
