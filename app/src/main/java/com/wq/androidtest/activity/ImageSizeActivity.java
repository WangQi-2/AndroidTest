package com.wq.androidtest.activity;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wq.androidtest.R;
import com.wq.androidlibrary.util.BitmapUtil;
import com.wq.androidtest.util.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wangqi on 15/9/22.
 * todo 在drawable中会被放大两倍??? 通过styleArray获取会缩小九分之一???
 */
public class ImageSizeActivity extends BaseActivity {

    ScrollView scrollView;
    LinearLayout linearLayout;
    StringBuffer info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scrollView = new ScrollView(this);
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(scrollView);
        scrollView.addView(linearLayout);
        info = new StringBuffer();


        try {
            getImageSize(info, "star_yellow.png", "72x72", R.drawable.star_yellow);
            info.append("\n\n");
            getImageSize(info, "icon1.jpg", "180x180", R.drawable.icon1);
            info.append("\n\n");
            getImageSize(info, "bg4.jpg", "640x1136", R.drawable.bg4);
            info.append("\n\n");
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e(e.getMessage());
            info.append(e.getMessage());
        }


    }

    private void getImageSize(StringBuffer info, String filePath, String size, int id) throws IOException {

        info.append("file: " + filePath + size + "\n");
        Drawable drawable = getResources().getDrawable(id);
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        info.append("res drawable width :" + width + "\n");
        info.append("res drawable height :" + height + "\n");


        Bitmap bitmap = BitmapUtil.drawableToBitmap(drawable);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        info.append("res bitmap width :" + width + "\n");
        info.append("res bitmap height :" + height + "\n");
        addViews(bitmap, info);

        AssetManager assets = getAssets();
        InputStream inputStream = assets.open(filePath);
        bitmap = BitmapFactory.decodeStream(inputStream);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        info.append("assets default width :" + width + "\n");
        info.append("assets default height :" + height + "\n");
        addViews(bitmap, info);
        //
        inputStream.reset();
        Rect outPadding = new Rect();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = 1;
        BitmapFactory.decodeStream(inputStream, outPadding, options);
        width = options.outWidth;
        height = options.outHeight;
        info.append("assets justdecodeBounds width :" + width + "\n");
        info.append("assets justdecodeBounds height :" + height + "\n");
        info.append("assets outPadding :" + outPadding);

        options.inJustDecodeBounds = false;
        inputStream.reset();
        bitmap = BitmapFactory.decodeStream(inputStream, outPadding, options);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        info.append("assets sample 1 width :" + width + "\n");
        info.append("assets sample 1 height :" + height + "\n");
        info.append("assets sample 1 outPadding :" + outPadding);
        addViews(bitmap, info);

    }

    private void addViews(Bitmap bitmap, StringBuffer info) {
        TextView textView = new TextView(this);
        textView.setText(info.toString());
        info.delete(0, info.length());
        linearLayout.addView(textView);

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        linearLayout.addView(imageView);
    }
}
