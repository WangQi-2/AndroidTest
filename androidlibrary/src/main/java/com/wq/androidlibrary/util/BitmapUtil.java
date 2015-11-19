package com.wq.androidlibrary.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by wangqi on 15/9/9.
 */
public class BitmapUtil {

    public static Bitmap getBitmap(String url) {
        Bitmap bitmap = null;
        URLConnection con = null;
        InputStream is = null;
        try {
            URL imageURL = new URL(url);
            con = imageURL.openConnection();
            con.setDoInput(true);
            con.connect();
            is = con.getInputStream();
//            bitmap = BitmapFactory.decodeStream(is);
            bitmap = BitmapFactory.decodeStream(is, null, null);
        } catch (Exception e) {
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
            }
        }
        return bitmap;
    }

    public static Bitmap getBitmap(File file) {
        Bitmap bitmap = null;

        try {
            bitmap = BitmapFactory.decodeFile(file.getPath());
//            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        } catch (Exception e) {
            // TODO: 15/10/8 吞噬
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
        Bitmap bitmap = null;
        byte[] data = StreamUtil.getBytes(is);
        bitmap = getBitmap(data, desireWidth, desiredHeight);
        return bitmap;
    }

    public static Bitmap getBitmap(byte[] data, int desireWidth, int desiredHeight) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        int srcWidth = options.outWidth;
        int srcHeight = options.outHeight;
        float scale = getScale(srcWidth, srcHeight, desireWidth, desiredHeight);
        int dstWidth = (int) (srcWidth * scale);
        int dstHeight = (int) (srcHeight * scale);

        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = getSimpleSize(srcWidth, srcHeight, desireWidth, desiredHeight);
        options.inJustDecodeBounds = false;
        options.inDither = false;

        Bitmap resizeBitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
        if (resizeBitmap != null) {
            resizeBitmap = getCutBitmap(resizeBitmap, desireWidth, desiredHeight);
        }

        return resizeBitmap;
    }

    public static Bitmap getScaleBitmap(File file, int desireedWidth, int desiredHeight) {
        Bitmap resizedBitmap = null;
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            resizedBitmap = getBitmap(is, desireedWidth, desiredHeight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resizedBitmap;
    }

    public static Bitmap getBitmap(byte[] bytes) {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap getBitmap(View view) {
        Bitmap bitmap = null;
        bitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return bitmap;
    }

    public static Drawable getDrawable(View view) {
        Bitmap bitmap = getBitmap(view);
        return bitmapToDrawable(bitmap);
    }

    public static Bitmap getScaleBitmap(Bitmap bitmap, int desiredWidth, int desiredHeight) {
        int srcWidth = bitmap.getWidth();
        int srcHeight = bitmap.getHeight();
        float scale = getScale(srcWidth, srcHeight, desiredWidth, desiredHeight);


        return scaleBitmap(bitmap,scale);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, float scale) {
        Bitmap resizeBmp = null;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        resizeBmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return resizeBmp;
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
        Bitmap dst = Bitmap.createBitmap(src, offsetX, offsetY, desiredWidth, desireHeight);
        return dst;
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
        int sampleSize = 1;
        while ((sampleSize * 2) <= ratio) {
            sampleSize *= 2;
        }
        return sampleSize;
    }

    public static Bitmap drawableToBitmap2(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable bitmapToDrawable(Resources resources, Bitmap bitmap) {
        return new BitmapDrawable(resources, bitmap);
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    public static TransitionDrawable bitmapToTransitionDrawable(Resources resources, Bitmap bitmap) {
        TransitionDrawable transitionDrawable = null;
        transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(resources.getColor(android.R.color.transparent)), new BitmapDrawable(resources, bitmap)});
        return transitionDrawable;

    }

    public static TransitionDrawable drawableToTransitionDrawable(Resources resources, Drawable drawable) {
        return new TransitionDrawable(new Drawable[]{new ColorDrawable(resources.getColor(android.R.color.transparent)), drawable});
    }

    public static byte[] bitmapToBytes(Bitmap bitmap, Bitmap.CompressFormat compressFormat) {
        byte[] ret = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, outputStream);
        ret = outputStream.toByteArray();

        if (outputStream != null) {
            try {
                outputStream.close();
                outputStream = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, float degree) {
        Bitmap ret = null;
        Matrix matrix = new Matrix();
        matrix.setRotate(degree % 360);
        ret = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
        return ret;
    }

    public static Bitmap toRoundBitmap(Bitmap bitmap, int roundPx) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int minSide = Math.min(width, height);
        if (roundPx * 2 > minSide) {
            roundPx = minSide / 2;
        }
        Rect src = new Rect(0, 0, width, height);
        Rect dst = new Rect(0, 0, width, height);

        Bitmap ret = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRoundRect(new RectF(dst), roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return ret;
    }


    public static Bitmap toRoundBitmap(Bitmap bitmap) {
        int roundPx = Math.max(bitmap.getWidth(), bitmap.getHeight());
        return toRoundBitmap(bitmap, roundPx);
    }

    public static Bitmap toReflectionBitmap(Bitmap bitmap) {
        Bitmap ret;
        int reflectionGap = 1;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        Bitmap reflectionBmp = Bitmap.createBitmap(bitmap, 0, height / 2, 0, height, matrix, false);
        ret = Bitmap.createBitmap(width, (height + height / 2 + reflectionGap), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(ret);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawRect(0, height, width, height + reflectionGap, null);
        canvas.drawBitmap(reflectionBmp, 0, height + reflectionGap, null);
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0, ret.getHeight() + reflectionGap, 0x70ffffff, 0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawRect(0, height, width, ret.getHeight(), paint);
        return ret;
    }

    public static void releaseBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    public static void BitmapToFile(Bitmap bitmap, String filePath) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(filePath);
            byte[] bytes = bitmapToBytes(bitmap, Bitmap.CompressFormat.PNG);
            fos.write(bytes);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
