package com.wq.androidtest.view.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;

import com.android.volley.toolbox.NetworkImageView;
import com.wq.androidlibrary.util.BitmapUtil;

/**
 * Created by wangqi on 15/11/11.
 */
public class RoundCornerImageView extends NetworkImageView {
    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        if (bm == null || bm.getWidth() <= 0 || bm.getHeight() <= 0) {
            return;
        }
        int width = bm.getWidth();
        int heigth = bm.getHeight();
        Bitmap bitmap = BitmapUtil.toRoundBitmap(bm, width < heigth ? width
                : heigth);
        super.setImageBitmap(bitmap);
    }


}
