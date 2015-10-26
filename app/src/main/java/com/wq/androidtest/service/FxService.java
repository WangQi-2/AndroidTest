package com.wq.androidtest.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wq.androidtest.R;


public class FxService extends Service
{

    LinearLayout mFloatLayout;
    WindowManager.LayoutParams wmParams;
    WindowManager mWindowManager;

    Button mFloatView;

    private static final String TAG = "FxService";

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(TAG, "oncreat");
        createFloatView();
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    private void createFloatView()
    {
        wmParams = new WindowManager.LayoutParams();
        mWindowManager = (WindowManager)getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        wmParams.format = PixelFormat.RGBA_8888;
        wmParams.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        ;

        wmParams.gravity = Gravity.LEFT | Gravity.TOP;

        wmParams.x = 0;
        wmParams.y = 0;


        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout, null);
        mWindowManager.addView(mFloatLayout, wmParams);

        Log.i(TAG, "mFloatLayout-->left" + mFloatLayout.getLeft());
        Log.i(TAG, "mFloatLayout-->right" + mFloatLayout.getRight());
        Log.i(TAG, "mFloatLayout-->top" + mFloatLayout.getTop());
        Log.i(TAG, "mFloatLayout-->bottom" + mFloatLayout.getBottom());

        mFloatView = (Button)mFloatLayout.findViewById(R.id.float_id);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        Log.i(TAG, "Width/2--->" + mFloatView.getMeasuredWidth()/2);
        Log.i(TAG, "Height/2--->" + mFloatView.getMeasuredHeight()/2);
        mFloatView.setOnTouchListener(new View.OnTouchListener()
        {

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                wmParams.x = (int) event.getRawX() - mFloatView.getMeasuredWidth()/2;
                //Log.i(TAG, "Width/2--->" + mFloatView.getMeasuredWidth()/2);
                Log.i(TAG, "RawX" + event.getRawX());
                Log.i(TAG, "X" + event.getX());
                wmParams.y = (int) event.getRawY() - mFloatView.getMeasuredHeight()/2 - 25;
                // Log.i(TAG, "Width/2--->" + mFloatView.getMeasuredHeight()/2);
                Log.i(TAG, "RawY" + event.getRawY());
                Log.i(TAG, "Y" + event.getY());
                mWindowManager.updateViewLayout(mFloatLayout, wmParams);
                return false;
            }
        });

        mFloatView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(FxService.this, "onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if(mFloatLayout != null)
        {
            mWindowManager.removeView(mFloatLayout);
        }
    }

}
