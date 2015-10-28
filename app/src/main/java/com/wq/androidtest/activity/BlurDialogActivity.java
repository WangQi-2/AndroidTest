package com.wq.androidtest.activity;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.wq.androidtest.R;

/**
 * Created by wangqi on 15/9/22.
 */
public class BlurDialogActivity extends BaseActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_blur);
        Button button = (Button) findViewById(R.id.showdialog);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {


        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_ff);
        View decorView = getWindow().getDecorView();
        Display display = getWindowManager().getDefaultDisplay();

        Point outSize = new Point();
        display.getSize(outSize);
        Bitmap bitmap = Bitmap
                .createBitmap(outSize.x, outSize.y, Bitmap.Config.ARGB_4444);
        decorView.draw(new Canvas(bitmap));
        bitmap = reSize(bitmap, (float) 0.5);
        bitmap = blur(this, bitmap);
        ViewGroup viewGroup = (ViewGroup) dialog.findViewById(android.view.Window.ID_ANDROID_CONTENT);
        View bgView = viewGroup.getChildAt(0);
//        bgView.setBackgroundDrawable(new BitmapDrawable(bitmap));
        viewGroup.setBackgroundDrawable(new BitmapDrawable(bitmap));
        dialog.show();

    }

    public static Bitmap reSize(Bitmap bitmap, float scale) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);// scale为缩放比
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        if (bitmap != null && !bitmap.equals(bmp) && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        return bmp;
    }

    // 使用renderscript-v8.jar中的RenderScript，避免API Level带来的影响
    public static Bitmap blur(Context context, Bitmap srcBitmap) {
        Bitmap destBitmap = srcBitmap.copy(srcBitmap.getConfig(), true);
        RenderScript rs = RenderScript.create(context);
        Allocation input = Allocation.createFromBitmap(rs, srcBitmap,
                Allocation.MipmapControl.MIPMAP_NONE, Allocation.USAGE_SCRIPT);
        Allocation output = Allocation.createTyped(rs, input.getType());
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(20);// 模糊半径,最大25
        script.setInput(input);
        script.forEach(output);
        output.copyTo(destBitmap);
        // destBitmap = dim(destBitmap);
        return destBitmap;
    }

}
